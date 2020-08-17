package facade.handlers;

import java.util.List;

import javax.ejb.Remote;

import facade.dto.AulaDTO;
import facade.dto.InstalacaoDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
@Remote
public interface IAulasServiceRemote {

	public void criarAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException;
	
	public List<InstalacaoDTO> getInst() throws ApplicationException;
	
	public void ativaAula(String inst, String nomeAula, String ini, String end, int maxAlunos) throws ApplicationException;
	
	public AulaDTO[] getOcup(String inst, String date) throws ApplicationException;
	
	public List<ModalidadeDTO> getModalidades() throws ApplicationException;
}
