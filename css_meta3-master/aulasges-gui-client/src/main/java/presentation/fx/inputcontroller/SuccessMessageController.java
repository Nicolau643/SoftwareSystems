package presentation.fx.inputcontroller;

import java.io.IOException;

import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import presentation.fx.model.AtivarAulaModel;
import presentation.fx.model.CriarAulaModel;
import presentation.fx.model.InscreverAulaModel;

public class SuccessMessageController {
	
	@FXML
	private Label labelMessage;
	@FXML
	private Button okButton;
	
	private static String fxmlFile;
	private static int op;
	
	private IAulasServiceRemote aulasServices;
	private IInscreverServiceRemote inscreverServices;
	
	public void setServices(IAulasServiceRemote aulasServices, IInscreverServiceRemote inscreverServices) {
		this.aulasServices = aulasServices;
		this.inscreverServices = inscreverServices;
		
	}

	public void setLabel(String c) {
		if (c.equals("criada")) {
			labelMessage.setText("Aula Criada com Sucesso!!");
			fxmlFile = "/fxml/criarAula.fxml";
			op=0;
		}else if (c.equals("ativa")) {
			labelMessage.setText("Aula Ativada com Sucesso!!");
			fxmlFile = "/fxml/ativarAula.fxml";
			op=1;
		}else if (c.equals("inscrito")) {
			labelMessage.setText("Inscricao feita com Sucesso!!");
			fxmlFile = "/fxml/inscreverAula.fxml";
			op=2;
		}
		
	}
	
	@FXML
	public void okButton(ActionEvent event) throws IOException {
		FXMLLoader criarAulaLoader = new FXMLLoader(getClass().getResource(fxmlFile));
	    Stage stage = (Stage) okButton.getScene().getWindow();
        Scene scene = new Scene(criarAulaLoader.load());
        
        if (op==0) {
        	 CriarAulaController criarAulaController = criarAulaLoader.getController();    	
         	
         	CriarAulaModel menuInicialModel = new CriarAulaModel(aulasServices);
         	criarAulaController.setModel(menuInicialModel);
         	criarAulaController.setServices(aulasServices,inscreverServices);
		}else if (op==1) {
			AtivarAulaController criarAulaController = criarAulaLoader.getController();    	
	    	
	    	AtivarAulaModel menuInicialModel = new AtivarAulaModel(aulasServices);
	    	criarAulaController.setModel(menuInicialModel);
	    	criarAulaController.setServices(aulasServices,inscreverServices);
		}else {
			InscreverAulaController criarAulaController = criarAulaLoader.getController();    	
	    	
	    	InscreverAulaModel menuInicialModel = new InscreverAulaModel(inscreverServices);
	    	criarAulaController.setModel(menuInicialModel);
	    	criarAulaController.setServices(aulasServices,inscreverServices);
		}
        
        
        stage.setScene(scene);
	}
	
	
}
