package business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;

@Entity
public abstract class Inscricao {

	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Aula aula;
	
	@OneToOne
	private Utente utente;
	
	public Inscricao() {}
	
	public Inscricao(Aula aula, Utente utente) {
		this.aula = aula;
		this.utente = utente;
	}
	
	public Aula getAula() {
		return aula;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public abstract double getCusto();
	
	
}
