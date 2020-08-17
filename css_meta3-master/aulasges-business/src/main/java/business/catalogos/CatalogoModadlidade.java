package business.catalogos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import business.Modalidade;
import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoModadlidade {
	
	@PersistenceContext
	private EntityManager em;

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
			 List<String> aux = query.getResultList();
			 System.out.println(aux);
			return  aux.toArray(new String[0]);
		} catch (PersistenceException e) {
			throw new ApplicationException ("Erro em carregar nomes das modalidades", e);
		}
		
	}

	
	public List<Modalidade> getAllMod() throws ApplicationException{
		TypedQuery<Modalidade> query = em.createNamedQuery(Modalidade.GET_ALL, Modalidade.class);
		
		try {
			return  query.getResultList();
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
