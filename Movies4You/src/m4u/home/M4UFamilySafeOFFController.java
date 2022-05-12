package m4u.home;

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



public class M4UFamilySafeOFFController implements Initializable
{ 

    @FXML private AnchorPane anpFamilySafeOFF;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	}
	
	
	@FXML
	private void movie1(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img1";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie2(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img2";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie3(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img3";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie4(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img4";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie5(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img5";
		gotoTransaction();
	}

	
	@FXML
	private void movie6(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img6";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie7(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img7";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie8(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img8";
		gotoTransaction();
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
	
	
	@FXML
	private void movie11(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img11";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie12(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img12";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie13(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img13";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie14(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img14";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie15(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img15";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie16(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img16";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie17(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img17";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie18(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img18";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie19(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img19";
		gotoTransaction();
	}
	
	
	@FXML
	private void movie20(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = "img20";
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
