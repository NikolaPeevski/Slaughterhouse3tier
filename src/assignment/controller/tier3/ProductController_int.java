package assignment.controller.tier3;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import assignment.model.Tray;

public interface ProductController_int extends Remote {
	public void makeProducts(Tray trays) throws RemoteException;
}
