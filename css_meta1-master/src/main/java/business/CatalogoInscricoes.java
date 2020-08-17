package business;

import java.util.ArrayList;
import java.util.List;

public class CatalogoInscricoes {
	
	private List<Inscricao> data;

	public CatalogoInscricoes() {
		this.data = new ArrayList<>();
	}

	public void add(int tipoInscricao, Aula aula, Utente utente) {
		
		data.add(tipoInscricao == 0 ? new Regular(aula,utente) : new Avulso(aula,utente));
		
	}

}
