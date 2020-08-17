package client;

import facade.exceptions.ApplicationException;
import facade.services.AulasService;
import facade.services.InscreverService;
import facade.startup.AulasGes;

public class SimpleClientAulasGes {

	private AulasService aulasService;
	private InscreverService inscreverService;
	
	public SimpleClientAulasGes(AulasService aulasService, InscreverService inscreverService) {
		super();
		this.aulasService = aulasService;
		this.inscreverService = inscreverService;
	}
	
	
	
	public void cria() throws ApplicationException {
		int[] a = {1,3};
		aulasService.criarAula("tenis", "aula", a,"10:00", 9);
		aulasService.ativaAula("campo", "aula","2010-03-28" , "2010-04-28", 20);
		
		
		System.out.println("ola");
		
	}
	
	
	
	public static void main(String[] args) throws ApplicationException {
		AulasGes a = new AulasGes();
		
		AulasService aulasService = new AulasService(a.getCriaAulaHandler(), a.getAtivaAulaHandler(), a.getOcupHandler());
		InscreverService inscreverService = new InscreverService(a.getInscreverAulaHandler());
		
		SimpleClientAulasGes sc = new SimpleClientAulasGes(aulasService, inscreverService);
		
		sc.cria();
		
	}
	
}
