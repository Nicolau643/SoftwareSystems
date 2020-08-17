package business;

import static javax.persistence.CascadeType.ALL;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import facade.exceptions.ApplicationException;

@Entity
@NamedQueries({
@NamedQuery(name = Instalacao.GET_ALL_NAMES, query = "SELECT i.nome from Instalacao i "),
@NamedQuery(name = Instalacao.EXIST_BY_NAME, query = "SELECT COUNT(i) from Instalacao i WHERE i.nome = :" 
													+ Instalacao.INSTALACAO_NAME),
@NamedQuery(name = Instalacao.GET_ALL, query = "SELECT i from Instalacao i "),
@NamedQuery(name = Instalacao.GET_BY_NAME, query = "SELECT i from Instalacao i WHERE i.nome = :" + Instalacao.INSTALACAO_NAME)})
public class Instalacao {
	
	public static final String GET_ALL_NAMES = "Instalacao.getAllNames";

	public static final String GET_BY_NAME = "Instacao.getByName";
	public static final String INSTALACAO_NAME = "name";

	public static final String EXIST_BY_NAME = "Instacao.existByName";

	public static final String GET_ALL = "Instalacao.getAll";
	
	
	@Id 
	@GeneratedValue
	private int id;
	
	private String nome;
	
	private int maxLot;
	
	@ManyToMany
	private List<Modalidade> modalidades;
	
	@OneToMany(cascade = ALL,orphanRemoval = true, mappedBy = "instalacao")
	private List<Sessao> sessoes;
	
	@Enumerated(EnumType.STRING)
	private InstalacaoType typeIns;
	
	public Instalacao() {}
	
	public Instalacao(String nome, int maxLot, List<Modalidade> modalidades, InstalacaoType typeIns) {
		
		this.nome = nome;
		this.maxLot = maxLot;
		this.modalidades = modalidades;
		this.typeIns = typeIns;
		this.sessoes = new ArrayList<>();
		
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean modIsValid(Modalidade modalidade) {
		return modalidades.contains(modalidade);
	}
	
	//pode e deve de ser melhorado
	public boolean isfreeBetween(LocalDate newIni, LocalDate newEnd, Horario horario) {
		
		
		if (sessoes.isEmpty()) {
			return true;
		}
		
		ArrayList<DayOfWeek> a = new ArrayList<>();
		
		for (DayOfWeek dayOfWeek : horario.getDays()) {
			a.add(dayOfWeek);
		}
		
		for (Sessao s : sessoes) {
			
			if (a.contains(s.getDay())) {
				if (s.getDate().isAfter(newIni) && s.getDate().isBefore(newEnd)) { //bug se for a mesma
					if (!timeCheck(horario, s)) {
						return false;
					}
				}
			}
			
			
		}
		return true;
	}

	private boolean timeCheck(Horario horario, Sessao s) {
		return (horario.getHoraInicio().isBefore(s.getTimeInit())
			&& horario.getHoraInicio().plusMinutes(horario.getDuracoes()).isBefore(s.getTimeInit())
			)||(
			horario.getHoraInicio().isAfter(s.getTimeInit())
			&& horario.getHoraInicio().plusMinutes(horario.getDuracoes()).isAfter(s.getTimeInit())
			);
	}
	
	public int getMaxLot() {
		return maxLot;
	}
	/*
	public void fillSessions(Horario horario, LocalDate dateIni, LocalDate dateEnd, Aula aulaAtiva, int maxAlunos,Instalacao inst) {
		

		ArrayList<DayOfWeek> a = new ArrayList<>();
		
		for (DayOfWeek dayOfWeek : horario.getDays()) {
			a.add(dayOfWeek);
		}
		
		
		while (dateIni.compareTo(dateEnd) != 0) {
			
			if (a.contains(dateIni.getDayOfWeek())) {
				Sessao s = new Sessao(dateIni.getDayOfWeek(), dateIni, horario.getHoraInicio(), horario.getDuracoes(),aulaAtiva,maxAlunos,inst);
				sessoes.add(s);
				aulaAtiva.addSessao(s);
			}
			
			dateIni = dateIni.plusDays(1);
		}
		
	}
	*/
	public List<Modalidade> getModalidades() {
		return modalidades;
	}
	
	public List<Sessao> getSessoes() throws ApplicationException {
		
		/*TypedQuery<Sessao> query = em.createNamedQuery(Sessao.GET_ALL, Sessao.class);
	
		try {
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Sessao not found.", e);
		}*/
		return sessoes;
	}
	
	public InstalacaoType getType() {
		return typeIns;
	}

	public void fillSessoes(List<Sessao> sessoes2, Aula aula) {
		sessoes2.forEach(s -> {
			sessoes.add(s);
			aula.addSessao(s);
		});
		
	}

}
