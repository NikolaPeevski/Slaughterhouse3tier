package assignment.controller.tier2;

import java.rmi.Remote;
import java.rmi.RemoteException;

import assignment.model.Animal;

public interface Controller_Tier2_int extends Remote{
	int ProccessAnimal(Animal animal) throws Exception, RemoteException;
	String b(int b) throws RemoteException;
}
