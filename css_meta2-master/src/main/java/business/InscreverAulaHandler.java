package business;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import facade.exceptions.ApplicationException;

public class InscreverAulaHandler {

	private EntityManagerFactory emf;
	
	public InscreverAulaHandler(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public String[] getModalidades() throws ApplicationException {
		
		EntityManager em = emf.createEntityManager();
		CatalogoModadlidade catModalidade = new CatalogoModadlidade(em);
		
		try {
			em.getTransaction().begin();
			String[] res = catModalidade.getAllNames();
			em.getTransaction().commit();
			return res;
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
		
	}
	
	//insc: 0 -> regular
	//		1 -> vulso
	public AulaDTO[] inscAula(String modalidade, int insc) throws ApplicationException {
		
		if (!(insc == 0 || insc == 1 )) {
			throw new ApplicationException("inscricao invalida!");
		}
		
		
		EntityManager em = emf.createEntityManager();
		CatalogoModadlidade catModalidade = new CatalogoModadlidade(em);
		CatalogoInstalacao catInstalacao = new CatalogoInstalacao(em);
		
		CatalogoAulas catAula = new CatalogoAulas(em);
		
		
		try {
			em.getTransaction().begin();
			List<AulaDTO> res;
			Modalidade mod = catModalidade.getByName(modalidade);
			if (insc == 0) { 
				res = regular(catAula, catInstalacao, mod); 
			}else {
				System.out.println("AQUI!!");
				res = avulso(catInstalacao, mod);	
			}
			em.getTransaction().commit();
			return res.toArray(new AulaDTO[0]);
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
		
	}

	private List<AulaDTO> avulso(CatalogoInstalacao catInstalacao, Modalidade mod) throws ApplicationException {
		List<AulaDTO> res;
		List<Aula> aulas;
		List<String> dias;
		List<Sessao> sessoes = catInstalacao.getSessoesModOneDay(mod);
		
		System.out.println(sessoes);
		
		dias = sessoes.stream()
				.filter(Sessao::hasVagas)
				.map(Sessao::getDay)
				.map(s -> s.toString())
				.collect(Collectors.toList());
		
		aulas = sessoes.stream()
				.filter(Sessao::hasVagas)
				.map(Sessao::getAula)
				.collect(Collectors.toList());		
				
				
		
		System.out.println(aulas);
		
		sort(aulas);
		
		res = new ArrayList<>();
		int i = 0;
		System.out.println("AQUI!!");
		for (Aula aula : aulas) {
			res.add(new AulaDTO(
					aula.getNome(),
					aula.getHorario().getHoraInicio().toString(),
					aula.getHorario().getDuracoes(),
					dias.get(i),
					aula.getActiveClass().getInst().getNome()));
			
			i++;
		}
		
		return res;
	}

	private List<AulaDTO> regular(CatalogoAulas catAula, CatalogoInstalacao catInstalacao, Modalidade mod) throws ApplicationException {
		
		List<AulaDTO> res;
		
		List<Aula> aulasMod = catAula.getByModalidade(mod);
		
		aulasMod = aulasMod.stream()
				.filter(Aula::isActive)
				.filter(a -> a.getActiveClass().hasVagas())
				.collect(Collectors.toList());
		
		sort(aulasMod);
		
		
		res = new ArrayList<>();
		
		for (int i = 0; i < aulasMod.size(); i++) {
			Aula aula = aulasMod.get(i);
			res.add(new AulaDTO(
					aula.getNome(),
					aula.getHorario().getHoraInicio().toString(),
					aula.getHorario().getDuracoes(),
					Arrays.stream(aula.getHorario().getDays()).map(DayOfWeek::toString).toArray(String[]::new),
					aula.getActiveClass().getInst().getNome(),
					aula.getActiveClass().getVagas()));
		}
		return res;
		
	}


	private void sort(List<Aula> aulas) {
		
		Collections.sort(aulas, new Comparator<Aula>() {

			@Override
			public int compare(Aula o1, Aula o2) {
				return o1.getHorario().getHoraInicio().compareTo(o2.getHorario().getHoraInicio());
			}
			
		});
	}
	
	public void escolhe(int typeInsc, AulaDTO aulaDto, int numUtente) throws ApplicationException {
		
		EntityManager em = emf.createEntityManager();
		CatalogoAulas catLectures = new CatalogoAulas(em);
		CatalogoUtente catUtente = new CatalogoUtente(em);
		CatalogoInscricoes catInscricoes = new CatalogoInscricoes(em);
		
		try {
			em.getTransaction().begin();
			
			Aula aula = catLectures.getByName(aulaDto.getNome());
			
			Utente utente = catUtente.getByNum(numUtente);
			
			if (typeInsc == 0) {
				
				if (!aula.getActiveClass().hasVagas()) {
					throw new ApplicationException("ja nao existem vagas regulares!");
				}
				
				aula.getActiveClass().decVaga();
				for (Sessao s : aula.getActiveClass().getSessoes()) {
					s.decVaga();
				}
				catInscricoes.addRegular(aula.getActiveClass().getSessoes(),aula,utente);
				
			}else {
				
				if (!hasVagasSessao(aulaDto, aula)) {
					throw new ApplicationException("ja nao existem vagas vulso!!");
				}
				
				for (Sessao s : aula.getActiveClass().getSessoes()) {
					if (s.getDay() == DayOfWeek.valueOf(aulaDto.getDiaSemana())) {
						s.decVaga();
						catInscricoes.addAvulso(s,aula,utente);
						break;
					}
				}
				
			}
			
			aula.getActiveClass().addUtente(utente);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
		
		
	}


	private boolean hasVagasSessao(AulaDTO aulaDto, Aula aula) {
		for (Sessao s : aula.getActiveClass().getSessoes()) {
			if (s.getDay() == DayOfWeek.valueOf(aulaDto.getDiaSemana())) {
				if (!s.hasVagas()) {
					return false;
				}else {
					return true;
				}
				
			}
		}
		return false;
	}
	
	
	public void populate() throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		CatalogoModadlidade catMod = new CatalogoModadlidade(em);
		CatalogoUtente catUtente = new CatalogoUtente(em);
		CatalogoInstalacao catInst = new CatalogoInstalacao(em);
		
		try {
			em.getTransaction().begin();
			Modalidade m1 = catMod.addModalidade("Pilates",50,7);
			Modalidade m2 = catMod.addModalidade("Step",45,10);
			Modalidade m3 = catMod.addModalidade("GAP",50,10);
			Modalidade m4 = catMod.addModalidade("Indoor Cycling",55,10);
			Modalidade m5 = catMod.addModalidade("Hidroginastica",45,15);
			catUtente.addUtente(1,"Ulisses",223842389);
			catUtente.addUtente(2,"David",256039682);
			catUtente.addUtente(3,"Teresa",269901841);
			catUtente.addUtente(4,"Querubim",197672337);
			catUtente.addUtente(5,"Cicero",221057552);
			
			ArrayList<Modalidade> ms1 = new ArrayList<>();
			ArrayList<Modalidade> ms2 = new ArrayList<>();
			ArrayList<Modalidade> ms3 = new ArrayList<>();
			ArrayList<Modalidade> ms4 = new ArrayList<>();
			
			ms1.add(m1);
			ms1.add(m2);
			ms1.add(m3);
			
			ms2.add(m1);
			
			ms3.add(m4);
			
			ms4.add(m5);
			
			catInst.addInst("Estudio 1",20,ms1,InstalacaoType.Estudio);
			catInst.addInst("Estudio 2",15,ms2,InstalacaoType.Estudio);
			catInst.addInst("Biclas",10,ms3,InstalacaoType.SalaDeBicicletas);
			catInst.addInst("Piscina 25",20,ms4,InstalacaoType.Piscina);
			
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
		
	}
	
	
}
