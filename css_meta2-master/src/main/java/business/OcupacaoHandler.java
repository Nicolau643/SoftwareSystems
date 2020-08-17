package business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import facade.exceptions.ApplicationException;

public class OcupacaoHandler {

	private EntityManagerFactory emf;
	
	public OcupacaoHandler(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public AulaDTO[] getOcupacao(String inst, String date) throws ApplicationException {
		
		EntityManager em = emf.createEntityManager();
		CatalogoInstalacao catInstalacao = new CatalogoInstalacao(em);
		
		try {
			em.getTransaction().begin();
			Instalacao instalacao = catInstalacao.getByName(inst);
			
			List<AulaDTO> res = new ArrayList<>();
			
			if (instalacao == null) {
				throw new ApplicationException("instalacao invalida!");
			}
			
			LocalDate newDate = LocalDate.parse(date);
			
			List<Sessao> sessoes = instalacao.getSessoes(em);
			
			for (Sessao s : sessoes) {
				if (s.getDate().compareTo(newDate) == 0) {
					res.add(new AulaDTO(s.getAula().getNome(),
							s.getAula().getHorario().getHoraInicio().toString(),
							s.getAula().getHorario().getDuracoes()
							));
				}
			}
			
			Collections.sort(res, new Comparator<AulaDTO>() {

				@Override
				public int compare(AulaDTO o1, AulaDTO o2) {
					return LocalTime.parse(o1.getHorario()).compareTo(LocalTime.parse(o2.getHorario()));
				}
			});
			
			em.getTransaction().commit();
			return res.toArray(new AulaDTO[0]);
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
		
		
		
	} 
	
	
}
