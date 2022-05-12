package m4u.transaction;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import m4u.model.Database;
import m4u.model.M4USharedData;


public class M4UTransactionController implements Initializable
{ 

    @FXML private AnchorPane anpTransaction;
    @FXML private ImageView mivMovieImage;
    @FXML private Label lblTitle;
    @FXML private Label lblYear;
    @FXML private Label lblQuality;
    @FXML private Label lblDuration;
    @FXML private Label lblRating;
    @FXML private Label lblGenre;
    @FXML private Button btnRent;
    @FXML private Button btnBuy;
    @FXML private TextArea txtDescription;
    @FXML private Hyperlink hypTrailer;
    
    private Database database = new Database();
    
    private String trailer = null;
    private String rentCost = null;
    private String buyCost = null;
    
    private String movieID = M4USharedData.MOVIE_ID;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		database.DBConnection();
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT *\r\n" + 
					"FROM movie  \r\n" + 
					"WHERE MovieID = '%s'", movieID);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the movie table		 
			if(database.resultSet.next())
			{
				// Set movie information
				lblTitle.setText(database.resultSet.getString("Title"));
				lblYear.setText(database.resultSet.getString("Year"));
				lblQuality.setText(database.resultSet.getString("Quality"));
				lblDuration.setText(database.resultSet.getString("Duration"));
				lblRating.setText(database.resultSet.getString("Rating"));
				lblGenre.setText(database.resultSet.getString("Genre"));
				txtDescription.setText(database.resultSet.getString("Description"));
				
				rentCost = database.resultSet.getString("RentPrice");
				buyCost = database.resultSet.getString("BuyPrice");
				trailer = database.resultSet.getString("Trailer");
			}
			
			// Set rent and buy prices
			btnBuy.setText("Buy   £" + buyCost);
			btnRent.setText("Rent   £" + rentCost);
			
			// Set movie image
			File file = new File("src/m4u/movieimage/" + movieID + ".jpg");
			Image img = new Image(file.toURI().toString());
			mivMovieImage.setImage(img);
			
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
	private void watchTrailer(ActionEvent event)
	{
		try {
		    Desktop.getDesktop().browse(new URL(trailer).toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
	
	@FXML
	private void rentMovie(ActionEvent event) throws IOException
	{
		M4USharedData.MOVIE_COST = rentCost;
		M4USharedData.MOVIE_PURCHASE_TYPE = "Rent";
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uPayment.fxml"));
		anpTransaction.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void buyMovie(ActionEvent event) throws IOException 
	{
		M4USharedData.MOVIE_COST = buyCost;
		M4USharedData.MOVIE_PURCHASE_TYPE = "Ownership";
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uPayment.fxml"));
		anpTransaction.getChildren().setAll(pane);
	}

	
	
}
