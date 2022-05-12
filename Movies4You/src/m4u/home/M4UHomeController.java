package m4u.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import m4u.model.Database;
import m4u.model.M4USharedData;


public class M4UHomeController implements Initializable
{ 
	@FXML private TextField txtSearchMovies;
    @FXML private Button btnFamilySafe;
    @FXML private MenuButton mbtnDisplayName;
    @FXML private AnchorPane anpHome;
    
    private Database database = new Database();
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// Displays customer "display name"
		mbtnDisplayName.setText("Hello " + Database.CUSTOMER_DISPLAYNAME);
		
		// Family Safe Default ON
		try {
			btnFamilySafe.setText("Family Safe - ON");
			btnFamilySafe.setStyle("-fx-text-fill: green");
			M4USharedData.FAMILY_SAFE = "ON";
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uFamilySafeON.fxml"));
			anpHome.getChildren().setAll(pane);
			anpHome.setPrefHeight(1300);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@FXML
	private void home(ActionEvent event) throws IOException
	{
		// Family Safe controller - OFF/ON
		if (M4USharedData.FAMILY_SAFE == "OFF")
		{
			btnFamilySafe.setText("Family Safe - OFF");
			btnFamilySafe.setStyle("-fx-text-fill: black");
			M4USharedData.FAMILY_SAFE = "OFF";
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uFamilySafeOFF.fxml"));
			anpHome.getChildren().setAll(pane);
			anpHome.setPrefHeight(1750);
		}
		else
		{
			btnFamilySafe.setText("Family Safe - ON");
			btnFamilySafe.setStyle("-fx-text-fill: green");
			M4USharedData.FAMILY_SAFE = "ON";
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uFamilySafeON.fxml"));
			anpHome.getChildren().setAll(pane);
			anpHome.setPrefHeight(1300);
		}
	}
	
	
	@FXML
	private void movieLibrary(ActionEvent event) throws IOException, SQLException
	{
		database.DBConnection();
		
		try {
			// Executes SQL query
			String query = String.format("SELECT PurchaseID from purchase WHERE Email = '%s'", Database.CUSTOMER_EMAIL);
					 
			database.resultSet = database.statement.executeQuery(query);
									 
			// gets the query result	 
			if(database.resultSet.next())
			{	
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../library/m4uMovieLibrary.fxml"));
				anpHome.getChildren().setAll(pane);
				anpHome.setPrefHeight(730);
			}
			else 
			{
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../library/m4uEmptyLibrary.fxml"));
				anpHome.getChildren().setAll(pane);
				anpHome.setPrefHeight(730);
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);}
		finally
		{
			database.connection.close();
			database.statement.close();
			database.resultSet.close();	}
	}
	
	
	@FXML
	private void account(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../account/m4uAccount.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}
	
	
	@FXML
	private void movieSearch(ActionEvent event) throws IOException, SQLException
	{
		database.DBConnection();
		
		String searchText = txtSearchMovies.getText().trim();
		
		if (searchText.isEmpty())
		{
			return;
		}
		 
		try {
			// Executes SQL query
			String query = String.format("SELECT MovieID, Title FROM movie WHERE Title = '%s'", searchText);
					 
			database.resultSet = database.statement.executeQuery(query);
									 
			// gets the query result	 
			if(database.resultSet.next())
			{
				txtSearchMovies.clear();
				
				M4USharedData.SEARCH_MOVIE_ID =  database.resultSet.getString("MovieID");
				M4USharedData.SEARCH_MOVIE_TITLE =  database.resultSet.getString("Title");
				
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../search/m4uSearch.fxml"));
				anpHome.getChildren().setAll(pane);
				anpHome.setPrefHeight(730);
			}
			else 
			{
				txtSearchMovies.clear();
				
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../search/m4uNoSearchResult.fxml"));
				anpHome.getChildren().setAll(pane);
				anpHome.setPrefHeight(730);
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);}
		finally
		{
			database.connection.close();
			database.statement.close();
			database.resultSet.close();	}
	}
	
	
	@FXML
	private void familySafe(ActionEvent event) throws IOException
	{
		// Family Safe controller - ON/OFF
		if (M4USharedData.FAMILY_SAFE == "ON")
		{
			btnFamilySafe.setText("Family Safe - OFF");
			btnFamilySafe.setStyle("-fx-text-fill: black");
			M4USharedData.FAMILY_SAFE = "OFF";
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uFamilySafeOFF.fxml"));
			anpHome.getChildren().setAll(pane);
			anpHome.setPrefHeight(1750);
		}
		else
		{
			btnFamilySafe.setText("Family Safe - ON");
			btnFamilySafe.setStyle("-fx-text-fill: green");
			M4USharedData.FAMILY_SAFE = "ON";
			AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uFamilySafeON.fxml"));
			anpHome.getChildren().setAll(pane);
			anpHome.setPrefHeight(1300);
		}
	}
	
	
	@FXML
	private void genreAction(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../genre/m4uAction.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}
	
	
	@FXML
	private void genreAdventure(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../genre/m4uAdventure.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}
	
	
	@FXML
	private void genreAnimation(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../genre/m4uAnimation.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}
	
	
	@FXML
	private void genreSciFi(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../genre/m4uSciFi.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}
	
	
	@FXML
	private void genreSport(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../genre/m4uSport.fxml"));
		anpHome.getChildren().setAll(pane);
		anpHome.setPrefHeight(730);
	}

}
