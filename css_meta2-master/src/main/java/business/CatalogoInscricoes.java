package business;

import java.util.List;

import javax.persistence.EntityManager;

public class CatalogoInscricoes {
	
	private EntityManager em;

	public CatalogoInscricoes(EntityManager em) {
		this.em = em;
	}
	
	public void addRegular( List<Sessao> sessoes, Aula aula, Utente utente) {
		em.persist(new Regular(sessoes,aula,utente));
		
	}

	public void addAvulso(Sessao s, Aula aula, Utente utente) {
		em.persist(new Avulso(s,aula,utente));
		
	}

	public Regular newRegular() {
		return new Regular();
	}


	public Avulso newAvulso() {
		return new Avulso();
	}

	

}
