package presentation.fx.model;

import auxiliarObjects.AulaDTOAux;
import facade.dto.AulaDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class OcupacaoModel {
	
	private StringProperty inst;
	private StringProperty data;
	private ObservableList<AulaDTOAux> aulas;

	public OcupacaoModel(IAulasServiceRemote aulasServices) {
		inst = new SimpleStringProperty();
		data = new SimpleStringProperty();
	}
	
	public StringProperty getDataProp() {
		return data;
	}
	public StringProperty getInstProp() {
		return inst;
	}
	public ObservableList<AulaDTOAux> getAulas() {
		return aulas;
	}
	
	public String getData() {
		return data.get();
	}
	public String getInst() {
		return inst.get();
	}

	public void procurar(IAulasServiceRemote aulasServices) {
		
		try {
			AulaDTO[] res = aulasServices.getOcup(getInst(), getData());
			AulaDTOAux ax;
			for (AulaDTO a : res) {
				ax = new AulaDTOAux(a.getNome(), a.getHorario(), a.getDuracao());
				aulas.add(ax);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
