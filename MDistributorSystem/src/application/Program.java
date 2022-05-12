package application;

import java.rmi.Naming;
import rmi.interphace.PackageInterface;

public class Program {
	
	public static void main(String[] args) 
	{
		
		String title = "Movie Title";
		String genre = "";
		String description = "";
		String year = "";
		String quality = "";
		String rating = "";
		String duration = "";
		String trailer = "";
		
		try 
		{
			PackageInterface service = (PackageInterface) Naming.lookup("rmi://localhost/PackageService");
			
			service.setMoviePackage(title, genre, description, year, quality, rating, duration, trailer);
			
			System.out.println("Package Successfully Sent!");
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		
//		String title = "Dolittle";
//		String genre = "Adventure";
//		String description = "Dr. John Dolittle lives in solitude behind the high walls..."
//				+ "His only companionship comes from an array of exotic animals..."
//				+ "But when young Queen Victoria becomes gravely ill, the eccentric doctor and his furry friends embark..";
//		String year = "2020";
//		String quality = "HD-4K";
//		String rating = "5.5";
//		String duration = "1h 41m";
//		String trailer = "https://www.youtube.com/watch?v=FEf412bSPLs";
		
		
//		String title = "Lego Batman";
//		String genre = "Adventure";
//		String description = "Lego batman description";
//		String year = "2017";
//		String quality = "HD-4K";
//		String rating = "7.0";
//		String duration = "1h 31m";
//		String trailer = "https://www.youtube.com/watch?v=h6DOpfJzmo0";
		
		
	}

}
