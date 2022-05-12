package m4u.model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertMessageBox 
{
	/**
	 * Age verification alert message box
	 */
	public static boolean isAgeVerified()
	{
		boolean over18 = false;
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO); 
		alert.setTitle("Movie4You - Age Verification");
		alert.setContentText("You need to confirm that you are over the age of 18 to sign up.\r\n" 
							+ "Are you over the age of 18?");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES)
		{
			over18 = true;
		}
		
		return over18;
	}
	
	
	/**
	 * Sign Up successful alert box
	 */
	public static void signUpSuccessful() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie4You");
		alert.setContentText("You have signed up successfully!");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		alert.showAndWait();
	}
	
	
	/**
	 * Payment successful alert box
	 */
	public static void paymentSuccessful() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie4You");
		alert.setContentText("Payment of £" + M4USharedData.MOVIE_COST + " has been accepted!");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		alert.showAndWait();
	}
	
	
	/**
	 * Change of password alert box
	 */
	public static void passwordChange() 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie4You");
		alert.setContentText("Your password has been changed successfully!");
		alert.setHeaderText(null);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(AlertMessageBox.class.getResource("../image/logo1.png").toString()));
		
		alert.showAndWait();
	}
	
}
