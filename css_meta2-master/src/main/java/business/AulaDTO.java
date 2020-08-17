package business;

import java.util.Arrays;

public class AulaDTO {
	private String nome;
	private String horario;
	private int duracao;
	private String[] diasSemana;
	private String nomeInst;
	private int vagas;
	private String diaSemana;
	
	
	public AulaDTO(String nome, String horario,int duracao, String[] diasSemana, String nomeInst, int vagas) {
		super();
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diasSemana = diasSemana;
		this.nomeInst = nomeInst;
		this.vagas = vagas;
		
	}


	public AulaDTO(String nome, String horario, int duracao, String diaSemana, String nomeInst) {
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diaSemana = diaSemana;
		this.nomeInst = nomeInst;
		
	}
	
	
	public AulaDTO(String nome, String horaInicio, int duracoes) {
		this.nome = nome;
		horario = horaInicio;
		duracao = duracoes;
	}


	public String getNome() {
		return nome;
	}

	public String getHorario() {
		return horario;
	}

	public String[] getDiasSemana() {
		return diasSemana;
	}

	public String getNomeInst() {
		return nomeInst;
	}

	public int getVagas() {
		return vagas;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public String getDiaSemana() {
		return diaSemana;
	}
	
	public String toStringOcup() {
		return "AulaDTO [nome=" + nome + ", horario=" + horario + ", duracao=" + duracao +"]";
	}

	public String toStringAvulso() {
		return "AulaDTO [nome=" + nome + ", horario=" + horario + ", duracao=" + duracao + ", diaSemana="
				+ diaSemana + ", nomeInst=" + nomeInst+"]";
	}

	@Override
	public String toString() {
		return "AulaDTO [nome=" + nome + ", horario=" + horario + ", duracao=" + duracao + ", diasSemana="
				+ Arrays.toString(diasSemana) + ", nomeInst=" + nomeInst + ", vagas=" + vagas + "]";
	}
	
	
}
