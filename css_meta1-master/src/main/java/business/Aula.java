package business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Aula {
	
	private String nome;
	private Horario horario;
	private Modalidade mod;
	private AulaAtiva activeClass;
	
	

	public Aula(Modalidade modalidade, String nomeAula) {
		this.mod = modalidade;
		this.nome = nomeAula;
	}

	
	public void setHorario(DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		this.horario = new Horario(days,horaInicio,duracoes);
	}
	
	public String getNome() {
		return nome;
	}

	public Modalidade getModalidade() {
		return mod;
	}

	
	public Horario getHorario() {
		return horario;
	}


	public void ativarAula(Instalacao newInst, LocalDate newIni, LocalDate newEnd, int maxAlunos) {
		activeClass = new AulaAtiva(newInst,newIni,newEnd,maxAlunos);
	}
	
	public boolean isActive() {
		return activeClass != null;
	}

	public AulaAtiva getActiveClass() {
		return activeClass;
	}


	public void addSessao(Sessao s) {
		activeClass.addSessao(s);
		
	}

	public Modalidade getMod() {
		return mod;
	}

	
}
