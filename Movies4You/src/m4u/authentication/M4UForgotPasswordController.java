package m4u.authentication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import m4u.model.AlertMessageBox;
import m4u.model.Database;

public class M4UForgotPasswordController implements Initializable{ 
	
    @FXML private AnchorPane ancpForgotPassword;
    @FXML private Label lblErrorMessage;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmPassword;
    
    private Database database = new Database();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		lblErrorMessage.setVisible(false);	
	}
	
	
	@FXML
	private void submit(ActionEvent event) throws IOException, SQLException
	{
		//Open Database
		database.DBConnection();
		
		String email = txtEmail.getText().toLowerCase();
		String newPassword = txtNewPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		
		//Trims white space
		email = email.trim();
		
		if (!email.isEmpty())
		{
			if (!newPassword.isEmpty()) 
			{
				try 
				{
					// SQL select statement
					String query = String.format("SELECT Email\r\n" + 
							"FROM customer\r\n" + 
							"WHERE Email = '%s'", email);
					
					// Executes SQL query
					database.resultSet = database.statement.executeQuery(query);
					
					// Gets the query result from the customer table		 
					if(database.resultSet.next())
					{
						//Confirms the new password
						if(confirmPassword.equals(newPassword))
						{
							//  SQL update statement
							String query1 = String.format("UPDATE customer\r\n" + 
									"SET Password = AES_ENCRYPT('%s','key1234')\r\n" + 
									"WHERE Email = '%s';", newPassword, email);
							
							// Executes SQL query
							database.statement.execute(query1);
							
							//Alert message box
							AlertMessageBox.passwordChange();
							
							// Back to login page
							goBack(event);
						}
						else 
						{
							lblErrorMessage.setText("Password does not match!");
							lblErrorMessage.setVisible(true);}
					}
					else 
					{
						lblErrorMessage.setText("Email does not exist!");
						lblErrorMessage.setVisible(true);}
					
				} catch (Exception e) {
					System.out.println(e);
				}
				finally {
					database.connection.close();
					database.statement.close();
					database.resultSet.close();}
			}
			else {
				lblErrorMessage.setText("All fields are required!");
				lblErrorMessage.setVisible(true);}
		}
		else {
			lblErrorMessage.setText("All fields are required!");
			lblErrorMessage.setVisible(true);}
	}
	
	
	
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uLogin.fxml"));
		ancpForgotPassword.getChildren().setAll(pane);
	}

}
