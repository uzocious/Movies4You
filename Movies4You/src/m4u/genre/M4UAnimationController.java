package m4u.genre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import m4u.model.M4USharedData;
import m4u.startGUI.StartM4U;



public class M4UAnimationController implements Initializable
{ 
    @FXML private AnchorPane anpAnimation;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
	}
	
	
	@FXML
	private void movie9(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img9";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie10(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img10";
		gotoTransaction();
	}
	
	
	private void gotoTransaction() throws IOException 
	{
		// Open Transaction Page
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../transaction/m4uTransaction.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Movies4You");
		primaryStage.getIcons().add(new Image(StartM4U.class.getResourceAsStream("../image/logo1.png")));
		primaryStage.showAndWait();
	}

}
