package business.catalogos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import business.Instalacao;
import business.InstalacaoType;
import business.Modalidade;
import business.Sessao;
import business.TodaysDate;
import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoInstalacao {
	@PersistenceContext
	private EntityManager em;

	public String[] getAllNames() throws ApplicationException {
		
		TypedQuery<String> query = em.createNamedQuery(Instalacao.GET_ALL_NAMES, String.class);
		
		try {
			return query.getResultList().toArray(new String[0]);
		} catch (PersistenceException e) {
			throw new ApplicationException ("Erro em carregar instalacoes", e);
		}
		
	}

	public boolean existByName(String inst) throws ApplicationException {
		TypedQuery<Integer> query = em.createNamedQuery(Instalacao.EXIST_BY_NAME, Integer.class);
		query.setParameter(Instalacao.INSTALACAO_NAME, inst);
		
		try {
			return query.getSingleResult() != 0;
		} catch (PersistenceException e) {
			throw new ApplicationException ("Instalacao dont exist.", e);
		}
		
	}

	public Instalacao getByName(String inst) throws ApplicationException {
		
		TypedQuery<Instalacao> query = em.createNamedQuery(Instalacao.GET_BY_NAME, Instalacao.class);
		query.setParameter(Instalacao.INSTALACAO_NAME, inst);
		
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Instalacao not found.", e);
		}
		
		
	}

	public List<Sessao> getSessoesModOneDay(Modalidade mod) throws ApplicationException {
		
		List<Sessao> res = new ArrayList<>();
		TypedQuery<Instalacao > query = em.createNamedQuery(Instalacao.GET_ALL, Instalacao.class);
		TypedQuery<Sessao> queryS = em.createNamedQuery(Sessao.GET_ALL, Sessao.class);
		
		try {
			List<Instalacao> instalacoes = query.getResultList();
			List<Sessao> sessoes = queryS.getResultList();  
			for (Instalacao i : instalacoes) {
				if (i.getModalidades().contains(mod)) { 
					for (Sessao s : sessoes) {
						if (s.getInstalacao().getNome().equals(i.getNome())) {
							LocalDateTime aux = LocalDateTime.of(s.getDate(), s.getTimeInit());
							if (aux.isAfter(TodaysDate.diaHoraCorrente) && aux.isBefore(TodaysDate.diaHoraCorrente.plusHours(24))) {
								res.add(s);
							}
						}
						
					}
				}
			}
			return res;
		}catch (PersistenceException e) {
			throw new ApplicationException ("Erro em carregar instalacoes", e);
		
		}
		
	}

	public List<Sessao> getSessoesMod(Modalidade mod) throws ApplicationException {
		
		List<Sessao> res = new ArrayList<>();
		TypedQuery<Instalacao > query = em.createNamedQuery(Instalacao.GET_ALL, Instalacao.class);
		TypedQuery<Sessao> queryS = em.createNamedQuery(Sessao.GET_ALL, Sessao.class);
		
		try {
			List<Instalacao> instalacoes = query.getResultList();
			instalacoes.forEach(i -> {
				if (i.getModalidades().contains(mod)) {
					List<Sessao> sessoes = queryS.getResultList();  
					sessoes.forEach(s -> {
						res.add(s);
					});
					
				}
				
			});
			return res;
		} catch (PersistenceException e) {
			throw new ApplicationException ("Erro em carregar instalacoes com a modalidade "+ mod.toString(), e);
		}
		
		
		
	}
	
	public List<Instalacao> getAllInstalacoes() throws ApplicationException{
		TypedQuery<Instalacao> query = em.createNamedQuery(Instalacao.GET_ALL, Instalacao.class);
		
		try {
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Instalacao not found.", e);
		}
		
	}

	public void addInst(String string, int i, List<Modalidade> ms1, InstalacaoType type) {
		Instalacao t = new Instalacao(string,i,ms1,type);
		em.persist(t);
		
	}

}
