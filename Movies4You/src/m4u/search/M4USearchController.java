package m4u.search;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import m4u.model.Database;
import m4u.model.M4USharedData;
import m4u.startGUI.StartM4U;



public class M4USearchController implements Initializable
{ 
    @FXML private AnchorPane anpSearch;
    @FXML private ImageView imgvMovieImage;
    @FXML private Label lblTitle;
    @FXML private Label lblYear;
    @FXML private Label lblQuality;
    @FXML private Label lblDuration;
    @FXML private Label lblRentPrice;
    @FXML private Label lblBuyPrice;
    @FXML private Label lblRating;
    @FXML private Label lblSerachText;
    
    private Database database = new Database();
    
    private String searchResultMovieID = M4USharedData.SEARCH_MOVIE_ID;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		database.DBConnection();
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT *\r\n" + 
					"FROM movie  \r\n" + 
					"WHERE MovieID = '%s'", searchResultMovieID);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the movie table		 
			if(database.resultSet.next())
			{
				// Set movie information
				lblSerachText.setText(M4USharedData.SEARCH_MOVIE_TITLE);
				lblTitle.setText(database.resultSet.getString("Title"));
				lblYear.setText(database.resultSet.getString("Year"));
				lblQuality.setText(database.resultSet.getString("Quality"));
				lblDuration.setText(database.resultSet.getString("Duration"));
				lblRating.setText(database.resultSet.getString("Rating"));
				lblRentPrice.setText("£"+database.resultSet.getString("RentPrice"));
				lblBuyPrice.setText("£"+database.resultSet.getString("BuyPrice"));
			}
			
			// Set movie image
			File file = new File("src/m4u/movieimage/" + searchResultMovieID + ".jpg");
			Image img = new Image(file.toURI().toString());
			imgvMovieImage.setImage(img);
			
		} 
		catch (Exception e) {
			System.out.println(e);}
		finally {
			try {
				database.connection.close();
				database.statement.close();
				database.resultSet.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}}
	}
	
	
	@FXML
	private void movie(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_ID = searchResultMovieID;
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
		primaryStage.setTitle("Movie4You");
		primaryStage.getIcons().add(new Image(StartM4U.class.getResourceAsStream("../image/logo1.png")));
		primaryStage.showAndWait();
	}


}
