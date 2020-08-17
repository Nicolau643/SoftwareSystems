package application;

import business.AulaDTO;
import business.InscreverAulaHandler;
import facade.exceptions.ApplicationException;

public class InscreverService {

	private InscreverAulaHandler inscreverAulaHandler;

	public InscreverService(InscreverAulaHandler inscreverAulaHandler) {
		super();
		this.inscreverAulaHandler = inscreverAulaHandler;
	}
	
	public String[] getModalidades() throws ApplicationException {
		return inscreverAulaHandler.getModalidades();
	}
	
	public AulaDTO[] inscAula(String modalidade, int insc) throws ApplicationException {
		return inscreverAulaHandler.inscAula(modalidade, insc);
	}
	
	public void escolhe(int inscType, AulaDTO aulaDto, int numUtente) throws ApplicationException {
		inscreverAulaHandler.escolhe(inscType,aulaDto, numUtente);
	}
	
	public void populate() throws ApplicationException {
		inscreverAulaHandler.populate();
	}
	
}
