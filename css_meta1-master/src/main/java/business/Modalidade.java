package business;

public class Modalidade {
	
	private String nome;
	private int duracaoMin;
	private int custoHora;
	
	
	public Modalidade(String nome, int duracaoMin, int custoHora) {
		super();
		this.nome = nome;
		this.duracaoMin = duracaoMin;
		this.custoHora = custoHora;
	}


	public String getName() {
		return nome;
	}


	public int getduracaoMin() {
		return duracaoMin;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modalidade other = (Modalidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public int getCustoHora() {
		return custoHora;
	}
}
