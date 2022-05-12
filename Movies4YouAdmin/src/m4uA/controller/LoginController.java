package m4uA.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import m4uA.model.Database;


public class LoginController implements Initializable
{
	@FXML private AnchorPane achpLogin;
	@FXML private TextField txtEmail;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblIncorrect;
	@FXML private Button btnLogin;
	
	private Database database = new Database();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lblIncorrect.setVisible(false);
		
	}
	
	@FXML
	private void login(ActionEvent event) throws SQLException
	{
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		
		// trims white spaces
		email = email.trim();
		
		//Open Database
		database.DBConnection();
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT * \r\n" + 
					"FROM admin  \r\n" + 
					"WHERE Email = '%s' AND Password = '%s'", email, password);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the customer table		 
			if(database.resultSet.next())
			{
				Database.ADMIN_EMAIL = database.resultSet.getString("Email");
				
				// Open Home Page
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
				achpLogin.getChildren().setAll(pane);
			}
			else
			{
				lblIncorrect.setVisible(true);
				return;
			}
			
		} catch (Exception e) {
			System.out.println(e);}
		finally {
			database.connection.close();
			database.statement.close();
			database.resultSet.close();}
	}
	

}
