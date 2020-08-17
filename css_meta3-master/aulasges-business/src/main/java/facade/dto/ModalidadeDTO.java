package facade.dto;

import java.io.Serializable;

public class ModalidadeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 871048340943606528L;
	
	private final int id;
	private final String name;
	
	public ModalidadeDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
