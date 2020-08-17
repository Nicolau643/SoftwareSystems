package business;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import facade.exceptions.ApplicationException;

public class CatalogoAulas {

	private EntityManager em;
	
	public CatalogoAulas(EntityManager em) {
		this.em = em;
	}

	public void add(Modalidade modalidade, String nomeAula, DayOfWeek[] days, LocalTime horaInicio, int duracoes) {
		
		Aula a = new Aula(modalidade,nomeAula);
		a.setHorario(days,horaInicio,duracoes);
		em.persist(a) ;
		
	}


	public boolean exists(String nomeAula) throws ApplicationException {
		
		TypedQuery<Integer> query = em.createNamedQuery(Aula.EXISTS_AULA, Integer.class);
		query.setParameter(Aula.AULA_NAME, nomeAula);
		
		try {
			return query.getSingleResult() != 0;
		} catch (PersistenceException e) {
			throw new ApplicationException ("Aula not found.", e);
		}
		
	}

	public Aula getByName(String nomeAula) throws ApplicationException {
		
		TypedQuery<Aula> query = em.createNamedQuery(Aula.GET_AULA, Aula.class);
		query.setParameter(Aula.AULA_NAME, nomeAula);
		
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Aula not found.", e);
		}
		
	}

	public List<Aula> getAllActive() throws ApplicationException {
		
		TypedQuery<Aula> query = em.createNamedQuery(Aula.GET_ATIVAS_AULA, Aula.class);
		
		try {
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Nao existem aulas ativas.", e);
		}
		
	}

	public List<Aula> getByModalidade(Modalidade mod) throws ApplicationException {
		
		
		TypedQuery<Aula> query = em.createNamedQuery(Aula.GET_BY_MOD, Aula.class);
		query.setParameter(Aula.MOD_NAME, mod.getName());
		
		try {
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Aula not found.", e);
		}
		
	}

}
