package business;

import java.util.ArrayList;
import java.util.List;

public class CatalogoUtente {

	private List<Utente> data;
	
	public CatalogoUtente() {
		this.data = new ArrayList<>();
	}

	public Utente getByNum(int numUtente) {
		for (Utente utente : data) {
			if (utente.getNumeroInsc() == numUtente) {
				return utente;
			}
		}
		return null;
	}

}
