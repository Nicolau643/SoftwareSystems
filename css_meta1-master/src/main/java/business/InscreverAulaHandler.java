package business;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import facade.exceptions.ApplicationException;

public class InscreverAulaHandler {

	private CatalogoAulas catLectures;
	private CatalogoInstalacao catInstalacao;
	private CatalogoModadlidade catModalidade;
	private CatalogoInscricoes catInscricoes;
	private CatalogoUtente catUtente;
	
	
	private static int tipoInscricao;
	
	
	public InscreverAulaHandler(CatalogoAulas catLectures, CatalogoInstalacao catInstalacao,
			CatalogoModadlidade catModalidade, CatalogoInscricoes catInscricoes, CatalogoUtente catUtente) {
		super();
		this.catLectures = catLectures;
		this.catInstalacao = catInstalacao;
		this.catModalidade = catModalidade;
		this.catInscricoes = catInscricoes;
		this.catUtente = catUtente;
	}

	public String[] getModalidades() {
		return catModalidade.getAllNames();
	}
	
	//insc: 0 -> regular
	//		1 -> vulso
	public AulaDTO[] inscAula(String modalidade, int insc) throws ApplicationException {
		
		Modalidade mod = catModalidade.getByName(modalidade);
		
		if (mod == null) {
			throw new ApplicationException("modalidade invalida!");
		}
		
		if (!(insc == 0 || insc == 1 )) {
			throw new ApplicationException("inscricao invalida!");
		}
		
		tipoInscricao = insc;
		List<AulaDTO> res;
		List<Aula> aulas;
		
		if (insc == 0) { // se for regular
			
			List<Sessao> sessoes = catInstalacao.getSessoesMod(mod);
			
			List<Integer> vagas = sessoes.stream()
					.filter(Sessao::hasVagas)
					.map(Sessao::getVagas)
					.collect(Collectors.toList());
			
			
			aulas = sessoes.stream()
					.filter(Sessao::hasVagas)
					.map(Sessao::getAula)
					.collect(Collectors.toList());
			
			
			sort(aulas);
			
			
			res = new ArrayList<>();
			
			for (int i = 0; i < aulas.size(); i++) {
				Aula aula = aulas.get(i);
				res.add(new AulaDTO(
						aula.getNome(),
						aula.getHorario().getHoraInicio().toString(),
						aula.getHorario().getDuracoes(),
						(String []) Arrays.stream(aula.getHorario().getDays()).map(DayOfWeek::toString).toArray(),
						aula.getActiveClass().getInst().getNome(),
						vagas.get(i)));
			} 
			
		}else {
			
			List<Sessao> sessoes = catInstalacao.getSessoesModOneDay(mod);
			
			aulas = sessoes.stream()
					.filter(Sessao::hasVagas)
					.map(Sessao::getAula)
					.collect(Collectors.toList());
			
			sort(aulas);
			
			res = new ArrayList<>();
			
			for (Aula aula : aulas) {
				res.add(new AulaDTO(
						aula.getNome(),
						aula.getHorario().getHoraInicio().toString(),
						aula.getHorario().getDuracoes(),
						(String []) Arrays.stream(aula.getHorario().getDays()).map(DayOfWeek::toString).toArray(),
						aula.getActiveClass().getInst().getNome()));
			}
			
		}
		
		
		
		return (AulaDTO[]) res.toArray();
		
	}


	private void sort(List<Aula> aulas) {
		
		Collections.sort(aulas, new Comparator<Aula>() {

			@Override
			public int compare(Aula o1, Aula o2) {
				return o1.getHorario().getHoraInicio().compareTo(o2.getHorario().getHoraInicio());
			}
			
		});
	}
	
	public void escolhe(AulaDTO aulaDto, int numUtente) throws ApplicationException {
		
		Aula aula = catLectures.getByName(aulaDto.getNome());
		
		if (aula == null) {
			throw new ApplicationException("aula invalida!");
		}
		
		Utente utente = catUtente.getByNum(numUtente);
		
		if (utente == null) {
			throw new ApplicationException("utente invalido!");
		}
		
		
		if (tipoInscricao == 0 && !aula.getActiveClass().hasVagas()) {
			throw new ApplicationException("ja nao existem vagas regulares!");
		}else if (tipoInscricao == 1 && !hasVagasSessao(aulaDto, aula)) {
			throw new ApplicationException("ja nao existem vagas vulso!!");	
		}
		
		
		if (tipoInscricao == 0) {
			aula.getActiveClass().decVaga();
			for (Sessao s : aula.getActiveClass().getSessoes()) {
				s.decVaga();
			}
		}else {
			for (Sessao s : aula.getActiveClass().getSessoes()) {
				if (s.getDay() == DayOfWeek.valueOf(aulaDto.getDiasSemana()[0])) {
					s.decVaga();
					break;
				}
			}
		}
		
		aula.getActiveClass().addUtente(utente);
		catInscricoes.add(tipoInscricao,aula,utente);
	}


	private boolean hasVagasSessao(AulaDTO aulaDto, Aula aula) {
		for (Sessao s : aula.getActiveClass().getSessoes()) {
			if (s.getDay() == DayOfWeek.valueOf(aulaDto.getDiasSemana()[0])) {
				if (!s.hasVagas()) {
					return false;
				}else {
					return true;
				}
				
			}
		}
		return false;
	}
	
	
}
