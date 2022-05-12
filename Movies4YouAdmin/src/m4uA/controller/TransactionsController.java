package m4uA.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import m4uA.model.AlertMessageBox;
import m4uA.model.Database;
import m4uA.model.PurchaseProperties;
import m4uA.model.RentalProperties;


public class TransactionsController implements Initializable
{
    @FXML private AnchorPane achpTransactions;
    @FXML private Label lblAdmin; 
    
    @FXML private TableView<RentalProperties> rentalTable;
    @FXML private TableColumn<RentalProperties, String> rentID;
    @FXML private TableColumn<RentalProperties, String> rentPrice;
    @FXML private TableColumn<RentalProperties, String> rentDate;
    @FXML private TableColumn<RentalProperties, String> rentMovieID;
    @FXML private TableColumn<RentalProperties, String> rentTitle;
    @FXML private TableColumn<RentalProperties, String> rentCustomer;
    
    @FXML private TableView<PurchaseProperties> purchaseTable;
    @FXML private TableColumn<PurchaseProperties, String> purchID;
    @FXML private TableColumn<PurchaseProperties, String> purchPrice;
    @FXML private TableColumn<PurchaseProperties, String> purchDate;
    @FXML private TableColumn<PurchaseProperties, String> purchMovieID;
    @FXML private TableColumn<PurchaseProperties, String> purchTitle;
    @FXML private TableColumn<PurchaseProperties, String> purchCustomer;
    
    private ObservableList<RentalProperties> _listOfRentals = FXCollections.observableArrayList();
    private ObservableList<PurchaseProperties> _listOfPurchases = FXCollections.observableArrayList();
	
    private Database database = new Database();
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lblAdmin.setText("ADMIN: " + Database.ADMIN_EMAIL);

		try {
			getRentalRecords();
			getPurchaseRecords();
		} 
		catch (SQLException e) {
			System.out.println(e);}
		
		initialzeRentalTableColumns();
		initialzePurchaseTableColumns();
		
		rentalTable.setItems(_listOfRentals);
		purchaseTable.setItems(_listOfPurchases);
		
	}
	
	
	private void initialzeRentalTableColumns() 
	{
		rentID.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("id"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("price"));
		rentDate.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("date"));
		rentMovieID.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("movieID"));
		rentTitle.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("title"));
		rentCustomer.setCellValueFactory(new PropertyValueFactory<RentalProperties, String>("customer"));
	}
	
	
	private void initialzePurchaseTableColumns() 
	{
		purchID.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("id"));
		purchPrice.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("price"));
		purchDate.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("date"));
		purchMovieID.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("movieID"));
		purchTitle.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("title"));
		purchCustomer.setCellValueFactory(new PropertyValueFactory<PurchaseProperties, String>("customer"));
	}
	

	// cancel rentals
	@FXML
	private void cancelRental(ActionEvent event) throws IOException, SQLException
	{
		if(!rentalTable.getSelectionModel().isEmpty()) 
		{
			boolean result = AlertMessageBox.cancelSelectedOrderConfirmationAlert();
			
			if(result == true)
			{
				String id = rentalTable.getSelectionModel().getSelectedItem().getId();
				
				database.DBConnection();
				
				try {
					// Executes SQL query
					String query = String.format("DELETE from purchase \r\n" + 
							"WHERE PurchaseID = '%s'", id);
					
					database.statement.execute(query);
				}
				catch(Exception e)
				{
					System.out.println(e);}
				finally
				{
					database.connection.close();
					database.statement.close();}
				
				// reload page
				reload(event);
			}
		}
		else 
		{
			AlertMessageBox.cancelSelectionOrderlErrorAlert();
		}
	}
	
	
	// cancel purchases
	@FXML
	private void cancelPurchase(ActionEvent event) throws IOException, SQLException
	{
		if(!purchaseTable.getSelectionModel().isEmpty()) 
		{
			boolean result = AlertMessageBox.cancelSelectedOrderConfirmationAlert();
			
			if(result == true)
			{
				String id = purchaseTable.getSelectionModel().getSelectedItem().getId();
				
				database.DBConnection();
				
				try {
					// Executes SQL query
					String query = String.format("DELETE from purchase \r\n" + 
							"WHERE PurchaseID = '%s'", id);
					
					database.statement.execute(query);
				}
				catch(Exception e)
				{
					System.out.println(e);}
				finally
				{
					database.connection.close();
					database.statement.close();}
				
				// reload page
				reload(event);
			}
		}
		else 
		{
			AlertMessageBox.cancelSelectionOrderlErrorAlert();
		}
	}
	
	// go to home page
	@FXML
	private void goHome(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
		achpTransactions.getChildren().setAll(pane);
	}
	
	
	// reload page
	@FXML
	private void reload(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Transactions.fxml"));
		achpTransactions.getChildren().setAll(pane);
	}
	
	
	// Gets all rental records from the database
	private void getRentalRecords() throws SQLException 
	{
		String id;
		String price;
		String date;
		String movieID;
		String title;
		String customer;
		
		database.DBConnection();
		 
		try {
			// Executes SQL query
			String query = String.format("SELECT purchase.PurchaseID, purchase.Price, purchase.Date, purchase.MovieID, movie.Title, purchase.Email \r\n" + 
					"FROM purchase\r\n" + 
					"INNER JOIN movie ON purchase.MovieID=movie.MovieID\r\n" + 
					"WHERE Type = 'Rent'");
					 
			database.resultSet = database.statement.executeQuery(query);
									 
			// gets the query result	 
			while(database.resultSet.next())
			{
				id = database.resultSet.getString("PurchaseID");
				price = database.resultSet.getString("Price");
				date = database.resultSet.getString("Date");
				movieID = database.resultSet.getString("MovieID");
				title = database.resultSet.getString("Title");
				customer = database.resultSet.getString("Email");
				
				_listOfRentals.add(new RentalProperties(id, price, date, movieID,title, customer));
			}
		}
		catch(Exception e) 
		{System.out.println(e);}
		finally{
			database.connection.close();
			database.statement.close();
			database.resultSet.close();}
	}
	
	
	// Gets all the purchase from the database
	private void getPurchaseRecords() throws SQLException 
	{
		String id;
		String price;
		String date;
		String movieID;
		String title;
		String customer;
		
		database.DBConnection();
		 
		try {
			// Executes SQL query
			String query = String.format("SELECT purchase.PurchaseID, purchase.Price, purchase.Date, purchase.MovieID, movie.Title, purchase.Email \r\n" + 
					"FROM purchase\r\n" + 
					"INNER JOIN movie ON purchase.MovieID=movie.MovieID\r\n" + 
					"WHERE Type = 'Ownership'");
					 
			database.resultSet = database.statement.executeQuery(query);
									 
			// gets the query result	 
			while(database.resultSet.next())
			{
				id = database.resultSet.getString("PurchaseID");
				price = database.resultSet.getString("Price");
				date = database.resultSet.getString("Date");
				movieID = database.resultSet.getString("MovieID");
				title = database.resultSet.getString("Title");
				customer = database.resultSet.getString("Email");
				
				_listOfPurchases.add(new PurchaseProperties(id, price, date, movieID, title, customer));
			}
		}
		catch(Exception e) 
		{System.out.println(e);}
		finally{
			database.connection.close();
			database.statement.close();
			database.resultSet.close();}
	}


}
