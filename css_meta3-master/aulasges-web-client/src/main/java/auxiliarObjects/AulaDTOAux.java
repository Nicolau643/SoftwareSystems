package auxiliarObjects;

public class AulaDTOAux{
	private String nome;
	private String horario;
	private String diasSemana;
	private String inst;
	private String vagas; 
	private String duracao;
	
	
	public AulaDTOAux(String nome,String horario,String diasSemana, String inst,int vagas) {
		this.nome = nome;
		this.horario = horario;
		this.diasSemana = convertDiasSemana(diasSemana);
		this.inst = inst;
		this.vagas = String.valueOf(vagas);
	}
	private String convertDiasSemana(String diasSemana2) {
		String[] dias = diasSemana2.split(" ");
		System.out.println(dias);
		StringBuilder res = new StringBuilder();
		for (String d : dias) {
			
			translateDay(res, d);
		}
		
		return res.toString().substring(0, res.length()-2);
	}
	private void translateDay(StringBuilder res, String d) {
		if (d.equals("MONDAY")) {
			res.append("Segunda");
		}else if (d.equals("TUESDAY")) {
			res.append("Terça");
		}else if (d.equals("WEDNESDAY")) {
			res.append("Quarta");
		}else if (d.equals("TUESDAY")) {
			res.append("Quinta");
		}else if (d.equals("FRIDAY")) {
			res.append("Sexta");
		}
		res.append(", ");
	}
	public AulaDTOAux(String nome,String horario,int duracao) {
		this.nome = nome;
		this.horario = horario;
		this.duracao = String.valueOf(duracao);
	}
	
	public String getNome() {
		return nome;
	}
	public String getDiasSemana() {
		return diasSemana;
	}
	public String getHorario() {
		return horario;
	}
	public String getInst() {
		return inst;
	}
	public String getVagas() {
		return vagas;
	}
	public String getDuracao() {
		return duracao;
	}
}
