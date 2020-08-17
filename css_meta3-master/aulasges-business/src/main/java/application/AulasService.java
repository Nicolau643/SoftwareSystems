package application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.AtivaAulaHandler;
import business.handlers.CriaAulaHandler;
import business.handlers.OcupacaoHandler;
import facade.dto.AulaDTO;
import facade.dto.InstalacaoDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;

@Stateless
public class AulasService implements IAulasServiceRemote {
	@EJB
	private CriaAulaHandler criaHandler;
	@EJB
	private AtivaAulaHandler ativaHandler;
	@EJB
	private OcupacaoHandler ocupHandler;
	

	//horainicio tipo hh:mm
	//days 1-7
	//dur minutos
	public void criarAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException {
		criaHandler.addAula(modalidade,nomeAula,days,horaInicio,duracoes);
	}
	
	public List<InstalacaoDTO> getInst() throws ApplicationException {
		return ativaHandler.getInstalacoes();
	}
	
	//ini/end -> yyyy-mm-dd
	public void ativaAula(String inst, String nomeAula, String ini, String end, int maxAlunos) throws ApplicationException {
		ativaHandler.ativaAula(inst,nomeAula,ini,end,maxAlunos);
	}
	
	public AulaDTO[] getOcup(String inst, String date) throws ApplicationException {
		return ocupHandler.getOcupacao(inst, date);
	}
	
	public List<ModalidadeDTO> getModalidades() throws ApplicationException {
		return criaHandler.getModalidades();
	}
	
}
