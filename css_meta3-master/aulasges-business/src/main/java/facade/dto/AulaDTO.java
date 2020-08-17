package facade.dto;

import java.io.Serializable;
import java.util.Arrays;

public class AulaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int id;
	private final String nome;
	private final String horario;
	private final int duracao;
	private final String[] diasSemana;
	private final String nomeInst;
	private final int vagas;
	private final String diaSemana;
	
	
	public AulaDTO(int id, String nome, String horario,int duracao, String[] diasSemana, String nomeInst, int vagas) {
		super();
		this.id = id;
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diasSemana = diasSemana;
		this.nomeInst = nomeInst;
		this.vagas = vagas;
		this.diaSemana = null;
		
	}


	public AulaDTO(int id,String nome, String horario, int duracao, String diaSemana, String nomeInst) {
		this.id = id;
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diaSemana = diaSemana;
		this.nomeInst = nomeInst;
		this.vagas = 0;
		this.diasSemana = null;
		
	}
	
	
	public AulaDTO(int id,String nome, String horaInicio, int duracoes) {
		this.id = id;
		this.nome = nome;
		horario = horaInicio;
		duracao = duracoes;
		this.diaSemana = null;
		this.nomeInst = null;
		this.vagas = 0;
		this.diasSemana = null;
	}
	public AulaDTO(int id,String nome, String diaSemana) {
		this.id = id;
		this.nome = nome;
		this.diaSemana = diaSemana;
		this.horario = null;
		this.duracao = 0;
		this.nomeInst = null;
		this.vagas = 0;
		this.diasSemana = null;
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
