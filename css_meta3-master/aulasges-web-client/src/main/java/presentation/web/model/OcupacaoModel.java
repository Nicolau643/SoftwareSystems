package presentation.web.model;

import java.util.LinkedList;
import java.util.List;

import auxiliarObjects.AulaDTOAux;
import facade.dto.AulaDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;

public class OcupacaoModel extends Model{

	private List<AulaDTOAux> aulas;
	private String nomeInst;
	private String data;
	
	private IAulasServiceRemote aulasServices;

	public void setHandler(IAulasServiceRemote aulasServices) {
		this.aulasServices = aulasServices;
		
	}
	
	public void setAulasB(){
		try {
			aulas = new LinkedList<>();
			AulaDTO[] res = aulasServices.getOcup(nomeInst, data);
			AulaDTOAux ax;
			for (AulaDTO a : res) {
				ax = new AulaDTOAux(a.getNome(), a.getHorario(), a.getDuracao());
				aulas.add(ax);
			}
		} catch (ApplicationException e) {
			aulas =  new LinkedList<>();
		}
		
	}
	
	public Iterable<AulaDTOAux> getAulas(){
		return aulas;
	}

	public String getNomeInst() {
		return nomeInst;
	}

	public void setNomeInst(String nomeInst) {
		this.nomeInst = nomeInst;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
