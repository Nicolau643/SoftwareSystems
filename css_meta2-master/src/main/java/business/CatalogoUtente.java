package business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import facade.exceptions.ApplicationException;

public class CatalogoUtente {

	private EntityManager em;
	
	public CatalogoUtente(EntityManager em) {
		this.em = em;
	}

	public Utente getByNum(int numUtente) throws ApplicationException {
		
		TypedQuery<Utente> query = em.createNamedQuery(Utente.GET_BY_NUM, Utente.class);
		query.setParameter(Utente.NUMBER, numUtente);
		
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Utente not found.", e);
		}
		
		
	}

	public void addUtente(int i, String string, int j) {
		em.persist(new Utente(i,string,j));
		
	}

}
