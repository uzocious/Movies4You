package rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.interphace.MoviePackage;
import rmi.interphace.PackageInterface;


public class PackageService extends UnicastRemoteObject implements PackageInterface
{
	
	private static final long serialVersionUID = -3908139395512675564L;
	
	private MoviePackage _mvPackage;
	
	public PackageService() throws RemoteException
	{}


	@Override
	public void setMoviePackage(String title, String genre, String description, String year, String quality,
			String rating, String duration, String trailer) throws RemoteException
	{
		this._mvPackage = new MoviePackage(title, genre, description, year, quality, rating, duration, trailer);
		
	}


	@Override
	public MoviePackage getMoviePackage() throws RemoteException 
	{
		return _mvPackage;
	}
	
	
	@Override
	public boolean isServerReady() throws RemoteException {
		return true;
	}

	
}
