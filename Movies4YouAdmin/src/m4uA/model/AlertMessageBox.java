package m4uA.model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AlertMessageBox 
{
	/**
	 * Cancel selected order confirmation alert
	 */
	public static boolean cancelSelectedOrderConfirmationAlert()
	{
		boolean result = false;
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO); 
		alert.setTitle("Movies4YouAdmin");
		alert.setContentText("Are you sure you want to cancel the selected order?");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		Optional<ButtonType> choice = alert.showAndWait();
		if (choice.get() == ButtonType.YES)
		{
			result = true;
		}
		
		return result;
	}
	
	
	/**
	 * Cancel selected order error alert
	 */
	public static void cancelSelectionOrderlErrorAlert() 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Movies4YouAdmin");
		alert.setContentText("Select from the table to cancel an order.");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		alert.showAndWait();
	}
	
	
	

	/**
	 * Post to server success alert
	 */
	public static void postToServerSuccessAlert() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie4YouAdmin");
		alert.setContentText("Post to server successful. 1 movie has been added.");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		alert.showAndWait();
	}
	
	
}
