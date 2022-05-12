package m4u.library;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import m4u.model.Database;
import m4u.model.M4UCustomerLibrary;


public class M4UMovieLibraryController implements Initializable
{ 
    @FXML private AnchorPane anpMovieLibrary;
    @FXML private ListView<String> lstMyMovies;
    @FXML private ListView<String> lstRented;
    @FXML private ImageView imgvMovieImage;
    @FXML private Button btnWatchMovie;
    
    private ArrayList<M4UCustomerLibrary> customerLibary = new ArrayList<M4UCustomerLibrary>();
    
    private Database database = new Database();
    private String watchMovieID;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		database.DBConnection();
		
		btnWatchMovie.setVisible(false);
		
		
		// Get customer library form database
		try {
			// Executes SQL query
			String query = String.format("SELECT purchase.MovieID, purchase.Type, movie.Title\r\n" + 
					"FROM purchase\r\n" + 
					"INNER JOIN movie ON purchase.MovieID=movie.MovieID\r\n" + 
					"WHERE purchase.Email = '%s'\r\n", Database.CUSTOMER_EMAIL);
		
			database.resultSet = database.statement.executeQuery(query);
			
			// gets the query result
			while(database.resultSet.next())
			{
				customerLibary.add(new M4UCustomerLibrary(database.resultSet.getString("purchase.MovieID"), 
						database.resultSet.getString("movie.Title"),
						database.resultSet.getString("purchase.Type")));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		finally
		{
			try {
				database.connection.close();
				database.statement.close();
				database.resultSet.close();
			}
			catch(Exception e )
			{
				System.out.println(e);
			}		
		}
		
		
		// Adding to list view
		for (M4UCustomerLibrary lib : customerLibary)
		{
			if (lib.getPurchaseType().equals("Ownership"))
			{
				lstMyMovies.getItems().add(lib.getMovieTitle());
			}
			else
			{
				lstRented.getItems().add(lib.getMovieTitle());
			}
		}
		

		// List view selection
		lstMyMovies.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		    	// Action here
		    	btnWatchMovie.setVisible(true);
		        
		        for (M4UCustomerLibrary lib : customerLibary)
		        {
		        	if (lib.getMovieTitle().equals(newValue))
		        	{
		        		watchMovieID = lib.getMovieID();
					}	
				}
		        
		        // Set movie image
				File file = new File("src/m4u/movieimage/" + watchMovieID + ".jpg");
				Image img = new Image(file.toURI().toString());
				imgvMovieImage.setImage(img);
				
		    }
		});
		
		lstRented.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		    	// Action here
		    	btnWatchMovie.setVisible(true);
		        
		        for (M4UCustomerLibrary lib : customerLibary)
		        {
		        	if (lib.getMovieTitle().equals(newValue))
		        	{
		        		watchMovieID = lib.getMovieID();
					}	
				}
		        
		        // Set movie image
				File file = new File("src/m4u/movieimage/" + watchMovieID + ".jpg");
				Image img = new Image(file.toURI().toString());
				imgvMovieImage.setImage(img);
		    }
		});
		
		
		// Sets list's selection mode to single
		lstMyMovies.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lstRented.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	
	
	@FXML
	private void watchMovie(ActionEvent event) throws IOException 
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../mediaplayer/m4uMediaPlayer.fxml"));
		anpMovieLibrary.getChildren().setAll(pane);
	}
	
	
	
}
