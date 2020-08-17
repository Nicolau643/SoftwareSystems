package facade.handlers;

import java.util.List;

import javax.ejb.Remote;

import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;


@Remote
public interface IInscreverServiceRemote {

	public List<ModalidadeDTO> getModalidades() throws ApplicationException;
	
	public AulaDTO[] inscAula(String modalidade, String insc) throws ApplicationException;
	
	public void escolhe(String inscType, AulaDTO aulaDto, int numUtente) throws ApplicationException;
	
}
