package business;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import facade.exceptions.ApplicationException;

public class CriaAulaHandler {

	private EntityManagerFactory emf;
	
	public CriaAulaHandler( EntityManagerFactory emf) {
		
		this.emf = emf;
	}


	public void addAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException {
		
		EntityManager em = emf.createEntityManager();
		CatalogoAulas catLectures = new CatalogoAulas(em);
		CatalogoModadlidade catModalidades = new CatalogoModadlidade(em);
		
		
		try {
			em.getTransaction().begin();
			Modalidade mod = catModalidades.getByName(modalidade);
			
			
			if(nomeAula.length() == 6 && isValidName(nomeAula) && catLectures.exists(nomeAula)) {
				throw new ApplicationException("nome de aula invalido! o nome tem 6 caracteres, pelo menos 3 alfanuméricos, "
						+ "não tem espaços, e é único");
			}
			
			if(duracoes <= 0) {
				throw new ApplicationException("duracoes nao sao positivas ou 0!");
			}
			
			if(!durationMoreThanMod(mod,duracoes)) {
				throw new ApplicationException("duracoes nao correspondem aos minimos da modalidade pretendida!");
			}
			
			LocalTime newhoraInicio =  convertBeginHours(horaInicio);
			DayOfWeek[] newDays = convertDays(days);
			
			catLectures.add(mod,nomeAula,newDays,newhoraInicio,duracoes);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error", e);
		}
	}


	private DayOfWeek[] convertDays(int[] days) throws ApplicationException {
		
		DayOfWeek[] res = new DayOfWeek[days.length];
		for (int i = 0; i < days.length; i++) {
			try {
				res[i] = DayOfWeek.of(days[i]);
			}catch(DateTimeParseException e) {
				throw new ApplicationException("Formatos de dias de inicio errados!");
			}
		}
		return res;
	}


	private LocalTime convertBeginHours(String horaInicio) throws ApplicationException {
		LocalTime res;
		
		try {
			res = LocalTime.parse(horaInicio);
		}catch(DateTimeParseException e) {
			throw new ApplicationException("Formatos de datas de inicio errados!");
		}

		return res;
	}


	private boolean durationMoreThanMod(Modalidade mod, int duracoes) {
		
		return duracoes >= mod.getduracaoMin();
	}

	private boolean isValidName(String nomeAula) {
		
		int numericCont = 0;
		
		for (int i = 0; i < nomeAula.length(); i++) {
			if (Character.isDigit(nomeAula.charAt(i))) {
				numericCont++;
			} else if (nomeAula.charAt(i) == ' ') {
				return false;
			}
		}
		
		return numericCont < 3;
	}
	
	
}
