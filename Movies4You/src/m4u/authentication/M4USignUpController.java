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

public class M4USignUpController implements Initializable
{
    @FXML private AnchorPane ancpSignUp;
    @FXML private Label lblErrorMessage;
    @FXML private TextField txtDisplayName;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    
    private Database database = new Database();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		lblErrorMessage.setVisible(false);
		
	}
	
	@FXML
	private void signMeUp(ActionEvent event) throws IOException, SQLException
	{
		//Open Database
		database.DBConnection();
		
		String displayName = txtDisplayName.getText().toLowerCase();
		String email = txtEmail.getText().toLowerCase();
		String password = txtPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		
		//Trims white space
		displayName = displayName.trim();
		email = email.trim();
		
		if(!displayName.isEmpty()) 
		{
			if (!email.isEmpty())
			{
				if (!password.isEmpty()) 
				{
					if(isEmailFormatValid(email) == true)
					{
						if(emailAlreadyExists(email) == false)
						{
							if(confirmPassword.equals(password))
							{
								try {
									
									// SQL insert statement
									String query = String.format("INSERT INTO customer (Email, DisplayName, Password)\r\n" + 
											"VALUES ('%s','%s',AES_ENCRYPT('%s','key1234'))", email, displayName, password);
									
									// Executes SQL query
									database.statement.execute(query);
										
									//Alert message box
									AlertMessageBox.signUpSuccessful();
									
									// Back to login page
									goBack(event);
								} 
								catch (Exception e){
									System.out.println(e);}
								finally {
									database.statement.close();
									database.connection.close();}
							}
							else {
								lblErrorMessage.setText("Password does not match!");
								lblErrorMessage.setVisible(true);}
						}
						else {
							lblErrorMessage.setText("Email already in use!");
							lblErrorMessage.setVisible(true);}
					}
					else {
						lblErrorMessage.setText("Email format is invalid!");
						lblErrorMessage.setVisible(true);}
				} 
				else {
					lblErrorMessage.setText("All fields are required!");
					lblErrorMessage.setVisible(true);}
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
		//Goes back to login page
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uLogin.fxml"));
		ancpSignUp.getChildren().setAll(pane);
	}
	
	
	
	// Checks Email Format Validity
	private boolean isEmailFormatValid(String email) 
	{
		boolean result = false;
		String pattern = "^.+@.+\\..+$";
		
		if(email.matches(pattern)){
			result = true;
		}		
		return result;
	}
	
	
	// Checks if email already exists in database
	private boolean emailAlreadyExists(String email) 
	{
		boolean result = false;
		
		try {
			// SQL select query
			String query = String.format("SELECT Email\r\n" + 
					"FROM customer\r\n" + 
					"WHERE Email = '%s'", email);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);

			// Gets the query result from the table customer		 
			if(database.resultSet.next())
			{result = true;}
		}
		catch(Exception e)
		{System.out.println(e);}
		
		return result;
	}
	
	

}
