package presentation.fx.model;

import java.util.List;

import facade.dto.InstalacaoDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtivarAulaModel {
	
	private final StringProperty instalacao;
	private final StringProperty aula;
	private final StringProperty dataIni;
	private final StringProperty dataEnd;
	private final IntegerProperty maxAlunos;
	private final ObservableList<String> instalacoes;
	
	public AtivarAulaModel(IAulasServiceRemote aulasServices) {
		instalacao = new SimpleStringProperty();
		aula = new SimpleStringProperty();
		dataIni = new SimpleStringProperty();
		dataEnd = new SimpleStringProperty();
		maxAlunos = new SimpleIntegerProperty();
		instalacoes = FXCollections.observableArrayList();
		try {
			List<InstalacaoDTO> inst = aulasServices.getInst();
			for (InstalacaoDTO i : inst) {
				instalacoes.add(i.getNome());
			}
		} catch (ApplicationException e) {
			// TODO: handle exception
		}
	}

	public StringProperty getInstalacaoProp() {
		return instalacao;
	}

	public StringProperty getAulaProp() {
		return aula;
	}

	public StringProperty getDataIniProp() {
		return dataIni;
	}

	public StringProperty getDataEndProp() {
		return dataEnd;
	}

	public IntegerProperty getMaxAlunosProp() {
		return maxAlunos;
	}

	public ObservableList<String> getInstalacoesProp() {
		return instalacoes;
	}
	
	public String getInstalacao() {
		return instalacao.get();
	}

	public String getAula() {
		return aula.get();
	}

	public String getDataIni() {
		return dataIni.get();
	}

	public String getDataEnd() {
		return dataEnd.get();
	}

	public Integer getMaxAlunos() {
		return maxAlunos.get();
	}

	public void clearProperties() {
		instalacao.set("");
		aula.set("");
		dataEnd.set("");
		dataIni.set("");
		maxAlunos.set(0);
		
	}

//	public ObservableList<String> getInstalacoes() {
//		return instalacoes;
//	}
	

}
