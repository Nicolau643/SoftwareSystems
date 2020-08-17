package presentation.fx.inputcontroller;

import java.io.IOException;

import auxiliarObjects.AulaDTOAux;
import facade.dto.AulaDTO;
import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.InscreverAulaModel;

public class InscreverAulaController {
	
	@FXML
	private ChoiceBox<String> modalidadeDropDown;
	@FXML
	private ChoiceBox<String> inscDropDown;
	@FXML
	private Button procurarButton;
	@FXML
	private TableView<AulaDTOAux> aulasTable;
	@FXML
	private TableColumn<AulaDTOAux, String> colunaNome;
	@FXML
	private TableColumn<AulaDTOAux, String> colunaHorario;
	@FXML
	private TableColumn<AulaDTOAux, String> diasSemanaColuna;
	@FXML
	private TableColumn<AulaDTOAux, String> instColuna;
	@FXML
	private TableColumn<AulaDTOAux, String> vagasColuna;
	
	@FXML
	private TextField nomeAulaTextField;
	@FXML
	private TextField numUtenteTextField;
	@FXML
	private Button inscButton;
	@FXML
	private Button voltarButton;
	@FXML
	private TextField diaDeSemanaTextField;
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	private InscreverAulaModel inscreverAulaModel;

	public void setModel(InscreverAulaModel inscreverAulaModel) {
		this.inscreverAulaModel = inscreverAulaModel;
		nomeAulaTextField.textProperty().bindBidirectional(inscreverAulaModel.getNomeAulaProp());
		numUtenteTextField.textProperty().bindBidirectional(inscreverAulaModel.getNumUtenteProp(), new NumberStringConverter());
		diaDeSemanaTextField.textProperty().bindBidirectional(inscreverAulaModel.getDiasProp());
		colunaNome.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("nome"));
		colunaHorario.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("horario"));
		diasSemanaColuna.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("diasSemana"));
		instColuna.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("inst"));
		vagasColuna.setCellValueFactory(new PropertyValueFactory<AulaDTOAux, String>("vagas"));
		modalidadeDropDown.setItems(inscreverAulaModel.getModalidades());
		inscDropDown.setItems(inscreverAulaModel.getInsc());
	}

	public void setServices(IAulasServiceRemote aulasServices, IInscreverServiceRemote inscreverServices) {
		this.aulasServices = aulasServices;
		this.inscreverServices = inscreverServices;
		
	}
	
	@FXML
	private void procurarAulasClick(ActionEvent event)  {
		inscreverAulaModel.getAulas(modalidadeDropDown.getValue(), inscDropDown.getValue(), inscreverServices);
		aulasTable.setItems(inscreverAulaModel.getAulas());
	}
	
	
	@FXML
	private void inscreverAulaClick(ActionEvent event)  {
		
		String valid = validateInput();
		
		if (valid.length() == 0) {
			try {
				AulaDTO res;
				if (modalidadeDropDown.getValue().equals("Regular")) {
					res = new AulaDTO(0,inscreverAulaModel.getNomeAula(), null);
				}else {
					res = new AulaDTO(0,inscreverAulaModel.getNomeAula(), inscreverAulaModel.getDias());
				}
				
				inscreverServices.escolhe(inscDropDown.getValue(), res, inscreverAulaModel.getNumUtente());
				inscreverAulaModel.clearProperties();
				showSuccess();
			} catch (ApplicationException | IOException e) {
				BaseController.showError(e.getMessage());
			}
		}else {
			BaseController.showError("input nao e valido!: "+ valid);
		}
	}


	
	private void showSuccess() throws IOException {
		FXMLLoader criadaLoader = new FXMLLoader(getClass().getResource("/fxml/aviso.fxml"));
	    Stage stage = (Stage) inscButton.getScene().getWindow();
        Scene scene = new Scene(criadaLoader.load());
        
        SuccessMessageController successController = criadaLoader.getController();    	
    	
        successController.setServices(aulasServices, inscreverServices);
        successController.setLabel("inscrito");
        stage.setScene(scene);
		
	}

	private String validateInput() {
		if (inscreverAulaModel.getNumUtente()<=0) {
			return "Numero negativo ou zero!";
		}
		return "";
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

}
