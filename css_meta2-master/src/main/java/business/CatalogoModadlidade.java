package business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import facade.exceptions.ApplicationException;

public class CatalogoModadlidade {

	private EntityManager em;
	
	public CatalogoModadlidade(EntityManager em) {
		this.em = em;
	}
	

	public Modalidade getByName(String modalidade) throws ApplicationException {
		
		TypedQuery<Modalidade> query = em.createNamedQuery(Modalidade.GET_BY_NAME, Modalidade.class);
		query.setParameter(Modalidade.MODALIDADE_NAME, modalidade);
		
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Modalidade dont exist.", e);
		}
		
	}

	public String[] getAllNames() throws ApplicationException {
		
		TypedQuery<String> query = em.createNamedQuery(Modalidade.GET_ALL_NAMES, String.class);
		
		try {
			return  query.getResultList().toArray(new String[0]);
		} catch (PersistenceException e) {
			throw new ApplicationException ("Erro em carregar nomes das modalidades", e);
		}
		
	}


	public Modalidade addModalidade(String nome, int i, int j) {
		Modalidade m = new Modalidade(nome,i,j);
		em.persist(m);
		return m;
	}

}
