package business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NamedQuery(name = Utente.GET_BY_NUM, query = "SELECT u FROM Utente u WHERE u.numeroInsc = :" 
												+ Utente.NUMBER)
public class Utente {
	
	public static final String GET_BY_NUM = "Utente.getByNum";
	public static final String NUMBER = "numeroInsc";
	@Id 
	@GeneratedValue
	private int id;
	private int numeroInsc;
	private String nome;
	private int nif;
	
	public Utente() {}
	
	public Utente(int numeroInsc, String nome, int nif) {
		super();
		this.numeroInsc = numeroInsc;
		this.nome = nome;
		this.nif = nif;
	}
	
	public int getNif() {
		return nif;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getNumeroInsc() {
		return numeroInsc;
	}

}
