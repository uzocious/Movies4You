package rmi.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PackageServer {

	/**
	 * Entry point of the server.
	 */
	public static void main (String[] args)
	{
		 System.out.println("Attempting to start Package Server...");
		
		 try
		 {
			PackageService service = new PackageService();
			 
			Registry registry = LocateRegistry.createRegistry(1099);
			
			registry.rebind("PackageService", service);
			
			System.out.println("Service started. Welcome to the RMI Package Service!");
			
		} 
		catch (RemoteException e)
		 {
			System.out.println("An error occured: "+e.toString());
			e.printStackTrace();
		}
	}
	
	
}
