package m4uA.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import m4uA.model.Database;
import m4uA.startGUI.StartM4UAdmin;


public class HomeController implements Initializable
{
    @FXML private AnchorPane achpHome;
    @FXML private Label lblAdmin;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lblAdmin.setText("ADMIN: " + Database.ADMIN_EMAIL);
	}
	
	
	@FXML
	private void addMovies(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/AddMovies.fxml"));
		achpHome.getChildren().setAll(pane);
	}

	
	@FXML
	private void transactions(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Transactions.fxml"));
		achpHome.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void logout(ActionEvent event)
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		
		StartM4UAdmin main = new StartM4UAdmin();
		
		Stage primaryStage = new Stage();
		
		main.start(primaryStage);
	}
	

}
