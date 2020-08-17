package business.catalogos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import business.Aula;
import business.Avulso;
import business.Regular;
import business.Sessao;
import business.Utente;
@Stateless
public class CatalogoInscricoes {
	
	@PersistenceContext
	private EntityManager em;

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
