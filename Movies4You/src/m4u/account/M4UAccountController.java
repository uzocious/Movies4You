package m4u.account;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import m4u.model.AlertMessageBox;
import m4u.model.Database;
import m4u.startGUI.StartM4U;


public class M4UAccountController implements Initializable
{ 
    @FXML private AnchorPane anpAccount;
    @FXML private Label lblDisplayName;
    @FXML private Label lblEmail;
    @FXML private Label lblErrorMessage;
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmPassword;
    
    private Database database = new Database();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		lblErrorMessage.setVisible(false);
		lblDisplayName.setText(Database.CUSTOMER_DISPLAYNAME);
		lblEmail.setText(Database.CUSTOMER_EMAIL);
	}
	
	@FXML
	private void changePassword(ActionEvent event) throws SQLException 
	{
		database.DBConnection();
		
		String email = Database.CUSTOMER_EMAIL;
		String newPassword = txtNewPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		
		if (!newPassword.isEmpty()) 
		{
			try 
			{
				//Confirms the new password
				if(confirmPassword.equals(newPassword))
				{
					//  SQL update statement
					String query = String.format("UPDATE customer\r\n" + 
							"SET Password = AES_ENCRYPT('%s','key1234')\r\n" + 
							"WHERE Email = '%s';", newPassword, email);
					
					// Executes SQL query
					database.statement.execute(query);
					
					// Alert message box
					AlertMessageBox.passwordChange();
					
					// Clears text box
					txtNewPassword.clear();
					txtConfirmPassword.clear();
					
					// Hide error message
					lblErrorMessage.setVisible(false);
				}
				else 
				{
					lblErrorMessage.setText("Password does not match!");
					lblErrorMessage.setVisible(true);}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			finally {
				database.connection.close();
				database.statement.close();}
		}
		else {
			lblErrorMessage.setText("All fields are required!");
			lblErrorMessage.setVisible(true);}
	}
	
	
	@FXML
	private void signOut(ActionEvent event) 
	{
		closeScene(event);
		Database.CUSTOMER_DISPLAYNAME = null;
		Database.CUSTOMER_EMAIL = null;
		
		StartM4U movies4You = new StartM4U();
		Stage primaryStage = new Stage();
		movies4You.start(primaryStage );
	}
	
	
	/**
	 * Closes current page
	 * @param event on-click action event which is invoked whenever the button is fired
	 */
	@FXML
	private void closeScene(ActionEvent event)
	{
		((Node)event.getSource()).getScene().getWindow().hide();
	}


}
