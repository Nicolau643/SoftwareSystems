package presentation.web.model;

import java.util.LinkedList;

import facade.dto.InstalacaoDTO;
import facade.handlers.IAulasServiceRemote;

public class AtivarAulaModel extends Model{
	
	private String instalacao;
	private String nomeAula;
	private String dataIni;
	private String dataFim;
	private String maxAlunos;
	
	private IAulasServiceRemote aulasServices;

	public void setHandler(IAulasServiceRemote aulasServices) {
		this.aulasServices = aulasServices;
	}
	
	
	public String getInstalacao() {
		return instalacao;
	}



	public void setInstalacao(String instalacao) {
		this.instalacao = instalacao;
	}



	public String getNomeAula() {
		return nomeAula;
	}



	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}



	public String getDataIni() {
		return dataIni;
	}



	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}



	public String getDataFim() {
		return dataFim;
	}



	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}



	public String getMaxAlunos() {
		return maxAlunos;
	}



	public void setMaxAlunos(String maxAlunos) {
		this.maxAlunos = maxAlunos;
	}



	public IAulasServiceRemote getAulasServices() {
		return aulasServices;
	}



	public void setAulasServices(IAulasServiceRemote aulasServices) {
		this.aulasServices = aulasServices;
	}



	public Iterable<InstalacaoDTO> getInstalacoes(){
		try {
			
			return aulasServices.getInst();
		} catch (Exception e) {
			return new LinkedList<>();
		}
		 
	}
	
	public void clearFields() {
		nomeAula = dataFim = dataIni = maxAlunos = "";
	}

}
