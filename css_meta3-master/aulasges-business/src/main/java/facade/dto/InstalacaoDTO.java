package facade.dto;

import java.io.Serializable;

public class InstalacaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int id;
	private final String nome;
	
	public InstalacaoDTO(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	
	
	
}
