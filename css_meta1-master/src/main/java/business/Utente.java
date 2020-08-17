package business;

public class Utente {
	
	private int numeroInsc;
	private String nome;
	private int nif;
	
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
