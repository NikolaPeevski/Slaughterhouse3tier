package assignment.controller.tier2;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import assignment.model.Animal;
import assignment.model.Part;

public class Controller_Tier2 extends UnicastRemoteObject implements Controller_Tier2_int, Serializable {
	private PartFactory factory;
	private int counter;
	private TrayController trayC;
	private Part[] buffer;

	public Controller_Tier2() throws RemoteException {
		super();
		factory = new PartFactory();
		trayC = new TrayController();
		counter = 0;
	}

	@Override
	public int ProccessAnimal(Animal animal) throws Exception {
		buffer = factory.makePart(counter, animal);
		counter = buffer[6].getID();
		trayC.insertIntrays(buffer);
		return 1;
	}
	public String b(int b){
		return "" + b;
	}
	public static void main(String[] args) throws Exception{
		java.rmi.registry.LocateRegistry.createRegistry(1099);
		Controller_Tier2 controller = new Controller_Tier2();
		Naming.rebind("Tier2_server", controller);
		System.out.println("Bounded");
	}

}
