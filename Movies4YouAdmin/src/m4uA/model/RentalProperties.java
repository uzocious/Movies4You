package m4uA.model;

import javafx.beans.property.SimpleStringProperty;

public class RentalProperties 
{
	private SimpleStringProperty id;
	private SimpleStringProperty price;
	private SimpleStringProperty date;
	private SimpleStringProperty movieID;
	private SimpleStringProperty title;
	private SimpleStringProperty customer;

	
	public RentalProperties(String id, String price, String date, String movieID, String title, String customer)
	{
		super();
		this.id = new SimpleStringProperty(id);
		this.price = new SimpleStringProperty(price);
		this.date = new SimpleStringProperty(date);
		this.movieID = new SimpleStringProperty(movieID);
		this.title = new SimpleStringProperty(title);
		this.customer = new SimpleStringProperty(customer);
	}

	public String getId() {
		return id.get();
	}

	public String getPrice() {
		return price.get();
	}

	public String getDate() {
		return date.get();
	}

	public String getMovieID() {
		return movieID.get();
	}
	
	public String getTitle() {
		return title.get();
	}

	public String getCustomer() {
		return customer.get();
	}
	
}
