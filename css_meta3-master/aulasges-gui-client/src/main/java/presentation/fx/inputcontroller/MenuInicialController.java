package presentation.fx.inputcontroller;

import java.io.IOException;

import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.fx.model.AtivarAulaModel;
import presentation.fx.model.CriarAulaModel;
import presentation.fx.model.InscreverAulaModel;
import presentation.fx.model.OcupacaoModel;

public class MenuInicialController {

	@FXML
	private Button ativarAulaButton;
	
	@FXML
	private Button criaAulaButton;
	
	@FXML
	private Button inscAulaButton;
	
	@FXML
	private Button ocupInstButton;
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	
	@FXML
	public void criarAula(ActionEvent event) throws IOException {
		FXMLLoader criarAulaLoader = new FXMLLoader(getClass().getResource("/fxml/criarAula.fxml"));
	    Stage stage = (Stage) criaAulaButton.getScene().getWindow();
        Scene scene = new Scene(criarAulaLoader.load());
        
        CriarAulaController criarAulaController = criarAulaLoader.getController();    	
    	
    	CriarAulaModel menuInicialModel = new CriarAulaModel(aulasServices);
    	criarAulaController.setModel(menuInicialModel);
    	criarAulaController.setServices(aulasServices,inscreverServices);
        
        stage.setScene(scene);
	
	}
	
	@FXML
	public void ativarAula(ActionEvent event) throws IOException {
		FXMLLoader ativarAulaLoader = new FXMLLoader(getClass().getResource("/fxml/ativarAula.fxml"));
	    Stage stage = (Stage) ativarAulaButton.getScene().getWindow();
        Scene scene = new Scene(ativarAulaLoader.load());
        
        AtivarAulaController ativarAulaController = ativarAulaLoader.getController();    	
    	
    	AtivarAulaModel ativarAulaModel = new AtivarAulaModel(aulasServices);
    	ativarAulaController.setModel(ativarAulaModel);
    	ativarAulaController.setServices(aulasServices,inscreverServices);
        
        stage.setScene(scene);
	}
	
	@FXML
	public void inscreverAula(ActionEvent event) throws IOException {
		FXMLLoader inscreverAulaLoader = new FXMLLoader(getClass().getResource("/fxml/inscreverAula.fxml"));
	    Stage stage = (Stage) inscAulaButton.getScene().getWindow();
        Scene scene = new Scene(inscreverAulaLoader.load());
        
        InscreverAulaController inscreverAulaController = inscreverAulaLoader.getController();    	
    	
    	InscreverAulaModel inscreverAulaModel = new InscreverAulaModel(inscreverServices);
    	inscreverAulaController.setModel(inscreverAulaModel);
    	inscreverAulaController.setServices(aulasServices,inscreverServices);
        
        stage.setScene(scene);
	}
	
	@FXML
	public void ocupacao(ActionEvent event) throws IOException {
		FXMLLoader ocupacaoLoader = new FXMLLoader(getClass().getResource("/fxml/ocupacao.fxml"));
	    Stage stage = (Stage) ocupInstButton.getScene().getWindow();
        Scene scene = new Scene(ocupacaoLoader.load());
        
        OcupacaoController ocupacacoController = ocupacaoLoader.getController();    	
    	
    	OcupacaoModel ocupacaoModel = new OcupacaoModel(aulasServices);
    	ocupacacoController.setModel(ocupacaoModel);
    	ocupacacoController.setServices(aulasServices,inscreverServices);
        
        stage.setScene(scene);
	}
	

	public void setServices(IAulasServiceRemote aulasServices, IInscreverServiceRemote inscreverServices) {
		this.inscreverServices = inscreverServices;
		this.aulasServices = aulasServices;
		
	}
	
	
}
