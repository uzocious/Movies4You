package m4u.transaction;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import m4u.model.AlertMessageBox;
import m4u.model.Database;
import m4u.model.M4USharedData;


public class M4UPaymentController implements Initializable
{ 

    @FXML private AnchorPane anpPayment;
    @FXML private TextField txtCardNumber;
    @FXML private TextField txtExpiryDate;
    @FXML private TextField txtSecurityCode;
    @FXML private Label lblTotalCost;
    @FXML private Label lblEDRequired;
    @FXML private Label lblCNRequired;
    @FXML private Label lblSCRequired;
    
    private Database database = new Database();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{	
		lblTotalCost.setText("£"+M4USharedData.MOVIE_COST);
		lblCNRequired.setVisible(false);
		lblEDRequired.setVisible(false);
		lblSCRequired.setVisible(false);
	}
	
	
	@FXML
	private void pay(ActionEvent event) throws SQLException
	{
		database.DBConnection();
		
		String cardNo = txtCardNumber.getText();
		String expireDate = txtExpiryDate.getText();
		String secureCode = txtSecurityCode.getText();
		
		cardNo = cardNo.trim();
		expireDate = expireDate.trim();
		secureCode = secureCode.trim();
		
		if (!cardNo.isEmpty())
		{
			lblCNRequired.setVisible(false);
			
			if (!expireDate.isEmpty())
			{
				lblEDRequired.setVisible(false);
				
				if (!secureCode.isEmpty())
				{
					lblSCRequired.setVisible(false);
					
					String purchaseID = autoGeneratePurchaseID();
					String type = M4USharedData.MOVIE_PURCHASE_TYPE;
					String price = M4USharedData.MOVIE_COST;
					String date = getCurrentDate();
					String movieID = M4USharedData.MOVIE_ID;
					String email = Database.CUSTOMER_EMAIL;
					
					// Send to database
					try {
						
						// SQL insert statement
						String query = String.format("INSERT INTO purchase (PurchaseID, Type, Price, Date, MovieID, Email)\r\n" + 
								"VALUES ('%s','%s','%s','%s','%s','%s')", purchaseID, type, price, date, movieID, email);
						
						// Executes SQL query
						database.statement.execute(query);
							
						//Alert message box
						AlertMessageBox.paymentSuccessful();
						
						// Default back to null
						M4USharedData.MOVIE_ID = null;
						M4USharedData.MOVIE_COST = null;
						M4USharedData.MOVIE_PURCHASE_TYPE = null;
						
						// Close window
						closeScene(event);

					} 
					catch (Exception e){
						System.out.println(e);}
					finally {
						database.statement.close();
						database.connection.close();}					
				}
				else 
				{
					lblSCRequired.setVisible(true);
					return;}
			}
			else 
			{
				lblEDRequired.setVisible(true);
				return;}
		}
		else 
		{
			lblCNRequired.setVisible(true);
			return;}
	}
	
	
	
	@FXML
	private void goBack(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("m4uTransaction.fxml"));
		anpPayment.getChildren().setAll(pane);
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
	

	// Auto Generate Purchase ID using Current Date
	private String autoGeneratePurchaseID() 
	{
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int second = now.getSecond();
		
		Random random = new Random();
		int randInt = random.nextInt(9999);
		
		String result =  String.format("PH%s%s%s%s%s", randInt, second, month, year,day);
		
		return result;
	}
	
	
	// Get Current Date
	private String getCurrentDate() 
	{
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		String result = String.format("%s/%s/%s", day,month,year);
		
		return result;
	}
	
	
}
