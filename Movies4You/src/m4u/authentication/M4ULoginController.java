package m4u.authentication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import m4u.model.AlertMessageBox;
import m4u.model.Database;
import m4u.startGUI.StartM4U;

public class M4ULoginController implements Initializable
{
	@FXML private AnchorPane ancpLogin;
	@FXML private Label lblErrorMessage;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private CheckBox chkRememberCredentials;

    //Database object
    private Database database = new Database();
    
    // String array
    private ArrayList<String> credenetial = new ArrayList<String>();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		lblErrorMessage.setVisible(false);
		
		// Get Credential
		getCredential();
		if(credenetial.size() != 0)
		{
			txtEmail.setText(credenetial.get(0));
			txtPassword.setText(credenetial.get(1));
			
			chkRememberCredentials.setSelected(true);
		}
	}
	
	@FXML
	private void login(ActionEvent event) throws IOException, SQLException
	{
		String email = txtEmail.getText().toLowerCase();
		String password = txtPassword.getText();
		
		//Trims white space
		email = email.trim();
		
		//Open Database
		database.DBConnection();
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT DisplayName, Email, AES_DECRYPT(Password, 'key1234')\r\n" + 
					"FROM customer  \r\n" + 
					"WHERE Email = '%s' AND Password = AES_ENCRYPT('%s', 'key1234')", email, password);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the customer table		 
			if(database.resultSet.next())
			{
				Database.CUSTOMER_EMAIL = database.resultSet.getString("Email");
				Database.CUSTOMER_DISPLAYNAME = database.resultSet.getString("DisplayName");
			}
			else
			{
				lblErrorMessage.setVisible(true);
				return;
			}
			
			// Set Credential
			setCredential(email, password);
			
			// Close Login Page
			closeScene(event);
			
			// Open Home Page
			homePage();
			
		} catch (Exception e) {
			System.out.println(e);}
		finally {
			database.connection.close();
			database.statement.close();
			database.resultSet.close();}
	}
	
	
	@FXML
	private void gotoForgotPassword(ActionEvent event) throws IOException
	{
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uForgotPassword.fxml"));
		ancpLogin.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void gotoSignUp(ActionEvent event) throws IOException
	{
		
		if (AlertMessageBox.isAgeVerified() == true)
		{
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uSignUp.fxml"));
			ancpLogin.getChildren().setAll(pane);
		}
	}
	
	
	
	/**
	 * Writes the user's credentials to a text file
	 */
	private void setCredential(String email, String password)
	{
		if (chkRememberCredentials.isSelected())
		{
			try 
			{
				FileWriter fWriter = new FileWriter("src/m4u/authentication/Credential.txt");
				PrintWriter pWriter = new PrintWriter(fWriter);
				
				pWriter.println(email);
				pWriter.println(password);
				
				pWriter.close();
				
			} 
			catch (Exception e)
			{
				System.err.println("Error Writing To Text File...");
			}
		}
	}
	
	
	/**
	 * Reads the saved credentials of the user from a text file
	 */
	private void getCredential()
	{
		try
		{
			FileReader fReader = new FileReader("src/m4u/authentication/Credential.txt");
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(fReader);
			
			while (scan.hasNextLine())
			{
				credenetial.add(scan.nextLine());
			}
		} 
		catch (FileNotFoundException e)
		{
			System.err.println("Error Reading From Text File...");
		}
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

	
	/**
	 * Open Home Page
	 * @throws IOException
	 */
	private void homePage() throws IOException 
	{
		// Open Home Page
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../home/m4uHome.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Movies4You");
		primaryStage.getIcons().add(new Image(StartM4U.class.getResourceAsStream("../image/logo1.png")));
		primaryStage.show();
	}
}
