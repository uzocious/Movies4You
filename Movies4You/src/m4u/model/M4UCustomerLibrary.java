package m4u.model;

public class M4UCustomerLibrary
{
	private String movieID;
	private String movieTitle;
	private String purchaseType;
	

	public M4UCustomerLibrary(String movieID, String movieTitle, String purchaseType) 
	{
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.purchaseType = purchaseType;
	}

	public String getMovieID() {
		return movieID;
	}


	public String getMovieTitle() {
		return movieTitle;
	}

	
	public String getPurchaseType() {
		return purchaseType;
	}
	
}
