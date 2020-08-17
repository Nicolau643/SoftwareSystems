package presentation.fx.model;

import java.util.List;

import facade.dto.ModalidadeDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CriarAulaModel {
	
	private final ObservableList<String> modalidadesNames;
	private final StringProperty mod;
	private final StringProperty nomeAula;
	private final StringProperty dias;
	private final StringProperty horaIni;
	private final IntegerProperty duracao;

	public CriarAulaModel(IAulasServiceRemote aulasServices) {
		this.mod = new SimpleStringProperty();
		this.nomeAula = new SimpleStringProperty();
		this.dias = new SimpleStringProperty();
		this.horaIni = new SimpleStringProperty();
		this.duracao = new SimpleIntegerProperty();
		this.modalidadesNames = FXCollections.observableArrayList();
		try {
			List<ModalidadeDTO> mods = aulasServices.getModalidades();
			
			for (ModalidadeDTO m : mods) {
				modalidadesNames.add(m.getName());
				
			}
			
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
	}
	
	public final IntegerProperty getDuracaoProp() {
		return duracao;
	}
	public final StringProperty getHoraIniProp() {
		return horaIni;
	}
	public final StringProperty getModProp() {
		return mod;
	}
	public final StringProperty getNomeAulaProp() {
		return nomeAula;
	}
	public final StringProperty getDiasProp() {
		return dias;
	}
	public final ObservableList<String> getModalidadesNames() {
		return modalidadesNames;
	}
	public Integer getDuracao() {
		return duracao.get();
	}
	public String getHoraIni() {
		return horaIni.get();
	}
	public String getMod() {
		return mod.get();
	}
	public String getNomeAula() {
		return nomeAula.get();
	}
	public String getDias() {
		return dias.get();
	}
	
	public final String[] getModalidadesNamesArray() {
		return modalidadesNames.toArray(new String[0]);
	}
	
	public void clearProperties() {
		mod.set("");
		nomeAula.set("");
		dias.set("");
		horaIni.set("");
		duracao.set(0);
	}
}
