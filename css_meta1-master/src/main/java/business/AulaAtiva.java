package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AulaAtiva {
	
	
	private Instalacao inst;
	private PeriodoTempo period;
	private int maxAlunos;
	private List<Utente> utentes;
	private int vagas;
	private List<Sessao> sessoes;
	

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
}
