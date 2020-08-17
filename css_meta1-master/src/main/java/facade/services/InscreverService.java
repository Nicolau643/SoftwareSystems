package facade.services;

import business.AulaDTO;
import business.InscreverAulaHandler;
import facade.exceptions.ApplicationException;

public class InscreverService {

	private InscreverAulaHandler inscreverAulaHandler;

	public InscreverService(InscreverAulaHandler inscreverAulaHandler) {
		super();
		this.inscreverAulaHandler = inscreverAulaHandler;
	}
	
	public String[] getModalidades() {
		return inscreverAulaHandler.getModalidades();
	}
	
	public AulaDTO[] inscAula(String modalidade, int insc) {
		return inscAula(modalidade, insc);
	}
	
	public void escolhe(AulaDTO aulaDto, int numUtente) throws ApplicationException {
		inscreverAulaHandler.escolhe(aulaDto, numUtente);
	}
	
}
