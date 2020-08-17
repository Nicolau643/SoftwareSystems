package presentation.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;

public class CriarAulaModel extends Model{
	
	//private List<String> modalidades;
	private String modalidade;
	private String nomeAula;
	private String dias;
	private String horaIni;
	private String duracao;
	private IAulasServiceRemote  aulasServices;

	/*public void setInicialState() {
		try {
			modalidades =  Arrays.asList(aulasServices.getModalidades());
		} catch (ApplicationException e) {
			modalidades = new ArrayList<>();
		}
	}*/
	public void setHandler(IAulasServiceRemote aulasService) {
		this.aulasServices = aulasService;
		
	}

	public Iterable<ModalidadeDTO> getModalidades() {
		try {
			return aulasServices.getModalidades();
		} catch (ApplicationException e) {
			return new ArrayList<>();
		}
	}

	/*public void setModalidades(List<String> modalidadesNames) {
		this.modalidades = modalidadesNames;
	}*/

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String mod) {
		this.modalidade = mod;
	}

	public String getNomeAula() {
		return nomeAula;
	}

	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public void clearFields() {
		nomeAula = dias = horaIni = duracao = "";
	}

	
	
	

}
