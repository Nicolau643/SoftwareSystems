package business;

public class AulaDTO {
	private String nome;
	private String horario;
	private int duracao;
	private String[] diasSemana;
	private String nomeInst;
	private int vagas;
	
	
	public AulaDTO(String nome, String horario,int duracao, String[] diasSemana, String nomeInst, int vagas) {
		super();
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diasSemana = diasSemana;
		this.nomeInst = nomeInst;
		this.vagas = vagas;
		
	}


	public AulaDTO(String nome, String horario, int duracao, String[] diasSemana, String nomeInst) {
		this.nome = nome;
		this.horario = horario;
		this.duracao = duracao;
		this.diasSemana = diasSemana;
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
}
