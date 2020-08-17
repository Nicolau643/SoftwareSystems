package presentation.fx;

import java.io.IOException;

import facade.handlers.IAulasServiceRemote;
import facade.handlers.IInscreverServiceRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.inputcontroller.MenuInicialController;

public class Startup extends Application {
    
	private static IAulasServiceRemote aulasServices;
	private static IInscreverServiceRemote inscreverServices;

	@Override 
    public void start(Stage stage) throws IOException {
    
		// This line to resolve keys against Bundle.properties
		// ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("en", "UK"));
        //ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
		
    	FXMLLoader createCustomerLoader = new FXMLLoader(getClass().getResource("/fxml/inicialMenu.fxml"));
    	Parent root = createCustomerLoader.load();
    	MenuInicialController menuInicialController = createCustomerLoader.getController();    	
    	
    	//MenuInicialModel menuInicialModel = new MenuInicialModel(aulasServices,inscreverServices);
    	//menuInicialController.setModel(menuInicialModel);
    	menuInicialController.setServices(aulasServices,inscreverServices);
    	//newCustomerController.setI18NBundle(i18nBundle);
    	
        Scene scene = new Scene(root, 600, 400);
       
        //stage.setTitle(i18nBundle.getString("application.title"));
        stage.setScene(scene);
        stage.show();
    }
	
	public static void startGUI(IAulasServiceRemote aulasServices,IInscreverServiceRemote inscreverServices) {
		Startup.aulasServices = aulasServices;
		Startup.inscreverServices = inscreverServices;
        launch();
	}
}
