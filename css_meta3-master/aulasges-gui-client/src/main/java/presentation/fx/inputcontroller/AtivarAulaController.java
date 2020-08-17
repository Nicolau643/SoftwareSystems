package presentation.fx.inputcontroller;

import java.io.IOException;

import facade.exceptions.ApplicationException;
import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.AtivarAulaModel;

public class AtivarAulaController {
	
	@FXML
	private ListView<String> nomesInstList;
	@FXML
	private TextField nomeAula;
	@FXML
	private TextField nomeInst;
	@FXML
	private TextField dataIni;
	@FXML
	private TextField dataEnd;
	@FXML
	private TextField maxAlunos;
	@FXML
	private Button ativarAulaButton;
	@FXML
	private Button voltarButton;
	
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	private AtivarAulaModel ativarAulaModel;
	

	public void setModel(AtivarAulaModel ativarAulaModel) {
		this.ativarAulaModel = ativarAulaModel;
		nomesInstList.setItems(ativarAulaModel.getInstalacoesProp());
		nomeAula.textProperty().bindBidirectional(ativarAulaModel.getAulaProp());
		nomeInst.textProperty().bindBidirectional(ativarAulaModel.getInstalacaoProp());
		dataIni.textProperty().bindBidirectional(ativarAulaModel.getDataIniProp());
		dataEnd.textProperty().bindBidirectional(ativarAulaModel.getDataEndProp());
		maxAlunos.textProperty().bindBidirectional(ativarAulaModel.getMaxAlunosProp(), new NumberStringConverter());
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
	private void ativarAulaClick(ActionEvent event)  {
		
		String valid = validateInput();
		
		if (valid.length() == 0) {
			
			try {
				aulasServices.ativaAula(ativarAulaModel.getInstalacao(),
						ativarAulaModel.getAula(),
						ativarAulaModel.getDataIni(),
						ativarAulaModel.getDataEnd(),
						ativarAulaModel.getMaxAlunos());
				ativarAulaModel.clearProperties();
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
	    Stage stage = (Stage) ativarAulaButton.getScene().getWindow();
        Scene scene = new Scene(criadaLoader.load());
        
        SuccessMessageController successController = criadaLoader.getController();    	
    	
        successController.setServices(aulasServices, inscreverServices);
        successController.setLabel("ativa");
        stage.setScene(scene);
		
	}

	private String validateInput() {
		if (!ativarAulaModel.getInstalacoesProp().contains(ativarAulaModel.getInstalacao())) {
			return "Instalacao nao existe!!";
		}
		return "";
	}
	
}
