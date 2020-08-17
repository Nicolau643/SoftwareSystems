package business;

public abstract class Inscricao {

	private Aula aula;
	private Utente utente;
	
	
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
