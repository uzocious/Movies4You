package rmi.interphace;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PackageInterface extends Remote 
{

	public void setMoviePackage(String title, String genre, String description, String year, String quality,
			String rating, String duration, String trailer) throws RemoteException;
	
	
	public MoviePackage getMoviePackage() throws RemoteException;
	 

	public boolean isServerReady() throws RemoteException;
	

}
