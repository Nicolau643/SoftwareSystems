package application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.InscreverAulaHandler;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IInscreverServiceRemote;

@Stateless
public class InscreverService implements IInscreverServiceRemote{

	@EJB
	private InscreverAulaHandler inscreverAulaHandler;
	
	public List<ModalidadeDTO> getModalidades() throws ApplicationException {
		return inscreverAulaHandler.getModalidades();
	}
	
	public AulaDTO[] inscAula(String modalidade, String insc) throws ApplicationException {
		return inscreverAulaHandler.inscAula(modalidade, insc);
	}
	
	public void escolhe(String inscType, AulaDTO aulaDto, int numUtente) throws ApplicationException {
		inscreverAulaHandler.escolhe(inscType,aulaDto, numUtente);
	}
	
	/*public void populate() throws ApplicationException {
		inscreverAulaHandler.populate();
	}*/
	
}
