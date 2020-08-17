package business.catalogos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import business.Utente;
import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoUtente {
	@PersistenceContext
	private EntityManager em;
	

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
