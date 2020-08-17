package business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Instalacao {
	
	private String nome;
	private int maxLot;
	private List<Modalidade> modalidades;
	private List<Sessao> sessoes;
	private InstalacaoType type;
	
	public Instalacao(String nome, int maxLot, List<Modalidade> modalidades) {
		super();
		this.nome = nome;
		this.maxLot = maxLot;
		this.modalidades = modalidades;
		this.type = InstalacaoType.DEFAULT;
		sessoes = new ArrayList<>();
		
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

	public void fillSessions(Horario horario, LocalDate dateIni, LocalDate dateEnd, Aula aulaAtiva) {
		

		ArrayList<DayOfWeek> a = new ArrayList<>();
		
		for (DayOfWeek dayOfWeek : horario.getDays()) {
			a.add(dayOfWeek);
		}
		
		
		while (dateIni.compareTo(dateEnd) != 0) {
			
			if (a.contains(dateIni.getDayOfWeek())) {
				Sessao s = new Sessao(dateIni.getDayOfWeek(), dateIni, horario.getHoraInicio(), horario.getDuracoes(),aulaAtiva);
				sessoes.add(s);
				aulaAtiva.addSessao(s);
			}
			
			dateIni = dateIni.plusDays(1);
		}
		
	}
	
	public List<Modalidade> getModalidades() {
		return modalidades;
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	public InstalacaoType getType() {
		return type;
	}

}
