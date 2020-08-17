package facade.startup;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import application.AulasService;
import application.InscreverService;
import business.AtivaAulaHandler;
import business.CriaAulaHandler;
import business.InscreverAulaHandler;
import business.OcupacaoHandler;
import facade.exceptions.ApplicationException;

public class AulasGes {
	
	private EntityManagerFactory emf;
	private AulasService aulasService;
	private InscreverService inscreverService;
	
	
	public void run() throws ApplicationException {
		// Connects to the database
		try {
			emf = Persistence.createEntityManagerFactory("domain-model-jpa");
			aulasService = new AulasService(new CriaAulaHandler(emf),new AtivaAulaHandler(emf),new OcupacaoHandler(emf));
			inscreverService = new InscreverService(new InscreverAulaHandler(emf));
		} catch (Exception e) {
			throw new ApplicationException("Error connecting database", e);
		}
	}
	
	public void stopRun() {
		// Closes the database connection
		emf.close();
	}
	
	public AulasService getAulasService() {
		return aulasService;
	}
	
	public InscreverService getInscreverService() {
		return inscreverService;
	}
	

}
