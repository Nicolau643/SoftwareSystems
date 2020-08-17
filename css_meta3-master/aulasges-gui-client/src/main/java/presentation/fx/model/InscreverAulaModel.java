package presentation.fx.model;

import java.util.List;

import auxiliarObjects.AulaDTOAux;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IInscreverServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InscreverAulaModel {
	
	private ObservableList<String> modalidades;
	private ObservableList<String> insc;
	private ObservableList<AulaDTOAux> aulas;
	private StringProperty nomeAula;
	private IntegerProperty numUtente;
	private StringProperty dias;
	
	

	public InscreverAulaModel(IInscreverServiceRemote inscreverServices) {
		modalidades = FXCollections.observableArrayList();
		try {
			List<ModalidadeDTO> ms = inscreverServices.getModalidades();
			for (ModalidadeDTO m : ms) {
				modalidades.add(m.getName());
			}
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
		insc = FXCollections.observableArrayList();
		insc.add("Regular");
		insc.add("Avulso");
		
		nomeAula = new SimpleStringProperty();
		numUtente = new SimpleIntegerProperty();
		dias = new SimpleStringProperty();
		aulas = FXCollections.observableArrayList();
		
	}
	
	public void getAulas(String m,String insI,IInscreverServiceRemote inscreverServices) {
		try {
			AulaDTO[] aul = inscreverServices.inscAula(m, insI);
			StringBuilder dias;
			for (AulaDTO aulaDTO : aul) {
				dias = new StringBuilder();
				for (String d : aulaDTO.getDiasSemana()) {
					dias.append(d+" ");
				}
				aulas.add(new AulaDTOAux(aulaDTO.getNome(), aulaDTO.getHorario(), dias.toString(), aulaDTO.getNomeInst(), aulaDTO.getVagas()));
			}
			
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
	}
	
	public ObservableList<String> getInsc() {
		return insc;
	}
	public ObservableList<String> getModalidades() {
		return modalidades;
	}
	
	public StringProperty getNomeAulaProp() {
		return nomeAula;
	}
	public IntegerProperty getNumUtenteProp() {
		return numUtente;
	}
	public StringProperty getDiasProp() {
		return dias;
	}
	public String getDias() {
		return dias.get();
	}
	public String getNomeAula() {
		return nomeAula.get();
	}
	public Integer getNumUtente() {
		return numUtente.get();
	}
	public ObservableList<AulaDTOAux> getAulas(){
		return aulas;
	}

	public void clearProperties() {
		aulas = FXCollections.observableArrayList();
		nomeAula.set("");
		numUtente.set(0);
	}

}
