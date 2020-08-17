package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.REMOVE;

@Entity
public class AulaAtiva {
	
	@Id 
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Instalacao inst;
	
	@Embedded
	private PeriodoTempo period;
	
	private int maxAlunos;
	
	@ManyToMany
	private List<Utente> utentes;
	
	private int vagas;
	
	@OneToMany
	private List<Sessao> sessoes;
	
	
	public AulaAtiva() {}

	public AulaAtiva(Instalacao inst, LocalDate dateIni, LocalDate newEnd, int maxAlunos) {
		this.inst = inst;
		this.period = new PeriodoTempo(dateIni, newEnd);
		this.maxAlunos = maxAlunos;
		this.utentes = new ArrayList<>();
		vagas = maxAlunos;
		sessoes = new ArrayList<>();
		
	}

	public Instalacao getInst() {
		return inst;
	}
	
	
	public boolean hasVagas() {
		return vagas !=0;
	}

	public void addSessao(Sessao s) {
		sessoes.add(s);
		
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void decVaga() {
		vagas--;
		
	}

	public void addUtente(Utente utente) {
		utentes.add(utente);
		
	}
	
	public int getMaxAlunos() {
		return maxAlunos;
	}
	
	public PeriodoTempo getPeriod() {
		return period;
	}

	public int getVagas() {
		return vagas;
	}
}
