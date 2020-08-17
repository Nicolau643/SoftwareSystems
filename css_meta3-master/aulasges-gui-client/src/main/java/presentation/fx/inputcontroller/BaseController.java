package presentation.fx.inputcontroller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BaseController {

	public static void showError(String errorText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(errorText);
		alert.showAndWait(); 
	}

	public static void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	


}
