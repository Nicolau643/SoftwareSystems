package business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Modalidade.GET_BY_NAME, query = "SELECT m FROM Modalidade m WHERE m.nome = :"
													+ Modalidade.MODALIDADE_NAME )
@NamedQuery(name = Modalidade.GET_ALL_NAMES, query = "Select m.nome FROM Modalidade m")
public class Modalidade {
	
	public static final String GET_BY_NAME = "Modalidade.getByName";
	public static final String MODALIDADE_NAME = "nome";
	
	public static final String GET_ALL_NAMES = "Modalidade.getAllNames";
	
	@Id 
	@GeneratedValue
	private int id;
	private String nome;
	private int duracaoMin;
	private int custoHora;
	
	public Modalidade() {}
	
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
	
	@Override
	public String toString() {
		return nome;
	}
}
