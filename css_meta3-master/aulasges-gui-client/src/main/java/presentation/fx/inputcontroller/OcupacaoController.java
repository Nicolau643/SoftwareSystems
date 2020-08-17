package presentation.fx.inputcontroller;

import java.io.IOException;

import auxiliarObjects.AulaDTOAux;
import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import presentation.fx.model.OcupacaoModel;

public class OcupacaoController {
	
	@FXML
	private TextField nomeInstTextField;
	@FXML
	private TextField dataTextField;
	@FXML
	private Button procurarTextField;
	@FXML
	private TableView<AulaDTOAux> aulasTable;
	@FXML
	private TableColumn<AulaDTOAux, String> nomeColumn;
	@FXML
	private TableColumn<AulaDTOAux, String> horarioColumn;
	@FXML
	private TableColumn<AulaDTOAux, Integer> duracaoColumn;
	@FXML
	private Button voltarButton;
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	private OcupacaoModel ocupacaoModel;
	
	public void setModel(OcupacaoModel ocupacaoModel) {
		this.ocupacaoModel = ocupacaoModel;
		nomeInstTextField.textProperty().bindBidirectional(ocupacaoModel.getInstProp());
		dataTextField.textProperty().bindBidirectional(ocupacaoModel.getDataProp());
		nomeColumn.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("nome"));
		horarioColumn.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("horario"));
		duracaoColumn.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, Integer>("duracao"));
	}

	public void setServices(IAulasServiceRemote aulasServices, IInscreverServiceRemote inscreverServices) {
		this.aulasServices = aulasServices;
		this.inscreverServices = inscreverServices;
		
	}
	
	@FXML
	private void voltarClick(ActionEvent event) throws IOException {
		
		FXMLLoader voltarLoader = new FXMLLoader(getClass().getResource("/fxml/inicialMenu.fxml"));
	    Stage stage = (Stage) voltarButton.getScene().getWindow();
        Scene scene = new Scene(voltarLoader.load());
        
        MenuInicialController inicialController = voltarLoader.getController();    	
    	
    	inicialController.setServices(aulasServices, inscreverServices);
        
        stage.setScene(scene);
		
	}
	
	@FXML
	private void procurarClick(ActionEvent event) throws IOException {
		ocupacaoModel.procurar(aulasServices);
		aulasTable.setItems(ocupacaoModel.getAulas());
	}

}
