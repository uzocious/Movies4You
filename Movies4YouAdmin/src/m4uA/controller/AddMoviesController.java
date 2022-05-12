package m4uA.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import m4uA.model.AlertMessageBox;
import m4uA.model.Database;
import rmi.interphace.PackageInterface;


public class AddMoviesController implements Initializable
{
    @FXML private AnchorPane achpAddMovies;
    @FXML private Label lblAdmin;
    @FXML private ComboBox<String> cobDistributors;
    @FXML private TextField txtIdNo;
    @FXML private TextArea txtPingResult;
    @FXML private TextArea txtRequestResult;
    @FXML private TextField txtRentalPrice;
    @FXML private TextField txtBuyingPrice;
    @FXML private TextField txtMovieID;
    @FXML private Label lblPostToServer;
	
    private ObservableList<String> movieDistributors =  FXCollections.observableArrayList("Warner Bros.", 
    		"Universal Pictures", "Sony Pictures", "Lionsgate", "20th Century Studios");
    
	private boolean pingSuccess = false;
    private boolean requestSuccess = false;
    
    private Database database = new Database();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lblAdmin.setText("ADMIN: " + Database.ADMIN_EMAIL);
		cobDistributors.setItems(movieDistributors);
		txtIdNo.setText("01M4U-678932898209");
	}
	

	@FXML
	private void ping(ActionEvent event) throws IOException
	{	
		if (cobDistributors.getValue() != null) 
		{
			try
			{
				PackageInterface service = (PackageInterface) Naming.lookup("rmi://localhost/PackageService");

				if(service.isServerReady() == true) 
				{
					txtPingResult.setText(
							"Pinging " + cobDistributors.getValue() + " [2a00:1450:4009:817::200e] with 32 bytes of data:\r\n" + 
							"Reply from 2a00:1450:4009:817::200e: time=20ms \r\n" + 
							"Reply from 2a00:1450:4009:817::200e: time=21ms"
							);
					
					pingSuccess = true;
				}
			} 
			catch (Exception e) 
			{
				txtPingResult.setText("Ping request could not find host " + cobDistributors.getValue() + ". Please try again later.");
			}
		}
		else 
		{
			txtPingResult.setText("Ping request could not find any host. Please select a distributor or try again later.");	
		}
		
		
	}
	
	
	@FXML
	private void packageRequest(ActionEvent event) throws IOException
	{	
		
		if(pingSuccess == true)
		{
			try {
				PackageInterface service = (PackageInterface) Naming.lookup("rmi://localhost/PackageService");
				
				txtRequestResult.setText(
						"Single package request results..." + "\r\n"
						+ "Title: " + service.getMoviePackage().get_title() + "\r\n"
						+ "Genre: " + service.getMoviePackage().get_genre() + "\r\n"
						+ "Description: " + service.getMoviePackage().get_description() + "\r\n"
						+ "Year: " + service.getMoviePackage().get_year() + "\r\n"
						+ "Quality: " + service.getMoviePackage().get_quality() + "\r\n"
						+ "Rating: " + service.getMoviePackage().get_rating() + "\r\n"
						+ "Duration: " + service.getMoviePackage().get_duration() + "\r\n"
						+ "Trailer: " + service.getMoviePackage().get_trailer()
						);
				
				requestSuccess = true;
			}
			catch (Exception e) {}
		}
		else 
		{
			txtRequestResult.setText("Packege request could not be completed. Ping a movie distributor or try again later.");
		}
	}
	
	
	@FXML
	private void postToServer(ActionEvent event) throws IOException
	{
		if(pingSuccess == true && requestSuccess == true)
		{
			String rentalPrice = txtRentalPrice.getText().trim();
			String buyingPrice= txtBuyingPrice.getText().trim();
			String movieID = txtMovieID.getText().trim();
			
			if (!rentalPrice.isEmpty() && !buyingPrice.isEmpty() && !movieID.isEmpty())
			{
				try {
					PackageInterface service = (PackageInterface) Naming.lookup("rmi://localhost/PackageService");
					
					String title = service.getMoviePackage().get_title();
					String genre = service.getMoviePackage().get_genre();
					String description = service.getMoviePackage().get_description();
					String year = service.getMoviePackage().get_year();
					String quality =  service.getMoviePackage().get_quality();
					String rating = service.getMoviePackage().get_rating();
					String duration = service.getMoviePackage().get_duration();
					String trailer = service.getMoviePackage().get_trailer();
					
					database.DBConnection();
					
					try {
						
						// SQL insert statement
						String query = String.format( "INSERT INTO movie (MovieID, Title, Genre, Description, Year, Quality, "
								+ "Rating, Duration, RentPrice, BuyPrice, Trailer)"
								+ " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
								movieID, title, genre, description, year, quality, rating, duration, rentalPrice, buyingPrice, trailer);
						
						// Executes SQL query
						database.statement.execute(query);
							
						//Alert message box
						AlertMessageBox.postToServerSuccessAlert();
						
						// reload page
						reload(event);
					} 
					catch (Exception e){
						System.out.println(e);}
					finally {
						database.statement.close();
						database.connection.close();}
					
				}
				catch (Exception e) {}
			}
			else
			{
				lblPostToServer.setText("Error Mesg: All fields are required.");
			}
		}
		else 
		{
			lblPostToServer.setText("Error Mesg: Ping and send a request.");
		}
	}
	
	
	@FXML
	private void goHome(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
		achpAddMovies.getChildren().setAll(pane);
	}
	
	
	private void reload(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/AddMovies.fxml"));
		achpAddMovies.getChildren().setAll(pane);
	}
	

}
