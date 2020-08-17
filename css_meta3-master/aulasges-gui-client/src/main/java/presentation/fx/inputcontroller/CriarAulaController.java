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
import presentation.fx.model.CriarAulaModel;

public class CriarAulaController {
	
	@FXML
	private ListView<String> listModCriaAula;
	@FXML
	private TextField textFieldMod;
	@FXML
	private TextField textFieldNomeAula;
	@FXML
	private TextField textFieldDias;
	@FXML
	private TextField textFieldHoraIni;
	@FXML
	private TextField textFieldDuracao;
	@FXML
	private Button criarButton;
	@FXML
	private Button voltarButton;
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	private CriarAulaModel criarAulaModel;
	
	/*@FXML
	private void initialize() throws ApplicationException {
		
		String[] nomesMod = aulasServices.getModalidades();

		listModCriaAula.setItems(FXCollections.observableArrayList(nomesMod));
		
	}*/
	
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
	private void criarAulaClick(ActionEvent event)  {
		
		String valid = validateInput();
		
		if (valid.length() == 0) {
			int[] dias = getIntValues(criarAulaModel.getDias());
			try {
				aulasServices.criarAula(criarAulaModel.getMod(), 
						criarAulaModel.getNomeAula(), 
						dias,
						criarAulaModel.getHoraIni(),
						criarAulaModel.getDuracao());
				criarAulaModel.clearProperties();
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
	    Stage stage = (Stage) criarButton.getScene().getWindow();
        Scene scene = new Scene(criadaLoader.load());
        
        SuccessMessageController successController = criadaLoader.getController();    	
    	
        successController.setServices(aulasServices, inscreverServices);
        successController.setLabel("criada");
        stage.setScene(scene);
		
	}

	private int[] getIntValues(String dias) {
		String[] diasInput = dias.split(",");
		int[] diasInt = new int[diasInput.length];
		int i=0;
		for (String d : diasInput) {
			if (d.equals("Segunda")) {
				diasInt[i] = 1;
			}else if (d.equals("Terça")) {
				diasInt[i] = 2;
			}else if (d.equals("Quarta")) {
				diasInt[i] = 3;
			}else if (d.equals("Quinta")) {
				diasInt[i] = 4;
			}else if (d.equals("Sexta")) {
				diasInt[i] = 5;
			}else {
				return null;
			}
			i++;
		}
		return diasInt;
	}

	private String validateInput() {
		if (getIntValues(criarAulaModel.getDias())==null) {
			return "dias nao validos";
		}
		if (!criarAulaModel.getHoraIni().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]")) {
			return "horas nao validas";
		}
		if (!criarAulaModel.getModalidadesNames().contains(criarAulaModel.getMod())) {
			return "modalidade nao valida";
		}
		return "";
	}

	public void setModel(CriarAulaModel criarAulaModel) {
		this.criarAulaModel = criarAulaModel;
		textFieldDias.textProperty().bindBidirectional(criarAulaModel.getDiasProp());
		textFieldDuracao.textProperty().bindBidirectional(criarAulaModel.getDuracaoProp(), new NumberStringConverter());
		textFieldHoraIni.textProperty().bindBidirectional(criarAulaModel.getHoraIniProp());
		textFieldMod.textProperty().bindBidirectional(criarAulaModel.getModProp());
		textFieldNomeAula.textProperty().bindBidirectional(criarAulaModel.getNomeAulaProp());
		listModCriaAula.setItems(criarAulaModel.getModalidadesNames());
	}

	public void setServices(IAulasServiceRemote aulasServices, IInscreverServiceRemote inscreverServices) {
		this.aulasServices = aulasServices;
		this.inscreverServices = inscreverServices;
		
	}

	
}
