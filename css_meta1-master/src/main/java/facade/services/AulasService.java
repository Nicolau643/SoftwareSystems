package facade.services;

import business.AtivaAulaHandler;
import business.AulaDTO;
import business.CriaAulaHandler;
import business.OcupacaoHandler;
import facade.exceptions.ApplicationException;

public class AulasService {
	
	private CriaAulaHandler criaHandler;
	private AtivaAulaHandler ativaHandler;
	private OcupacaoHandler ocupHandler;
	
	
	
	
	public AulasService(CriaAulaHandler criaHandler, AtivaAulaHandler ativaHandler, OcupacaoHandler ocupHandler) {
		super();
		this.criaHandler = criaHandler;
		this.ativaHandler = ativaHandler;
		this.ocupHandler = ocupHandler;
	}

	//horainicio tipo hh:mm
	//days 1-7
	//dur minutos
	public void criarAula(String modalidade, String nomeAula, int[] days, String horaInicio, int duracoes) throws ApplicationException {
		criaHandler.addAula(modalidade,nomeAula,days,horaInicio,duracoes);
	}
	
	public String[] getInst() {
		return ativaHandler.getInstalacoes();
	}
	
	//ini/end -> yyyy-mm-dd
	public void ativaAula(String inst, String nomeAula, String ini, String end, int maxAlunos) throws ApplicationException {
		ativaHandler.ativaAula(inst,nomeAula,ini,end,maxAlunos);
	}
	
	public AulaDTO[] getOcup(String inst, String date) throws ApplicationException {
		return ocupHandler.getOcupacao(inst, date);
	}
	
}
