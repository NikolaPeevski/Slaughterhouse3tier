package assignment.controller.tier3;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import assignment.model.Animal;
import assignment.model.HalfAnimal;
import assignment.model.Package;
import assignment.model.Part;
import assignment.model.Product;
import assignment.model.Tray;

public class ProductController extends UnicastRemoteObject implements ProductController_int, Serializable {
	
	private ArrayList<HalfAnimal> halfAnimalProduct;
	private ArrayList<Package> packageProduct;

	private ArrayList<Animal> animals;
	private ArrayList<Product> products;
	private int limit;
	private int counter;
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public ProductController() throws RemoteException {
		super();
		halfAnimalProduct = new ArrayList<HalfAnimal>();
		packageProduct = new ArrayList<Package>();
		animals = new ArrayList<Animal>();
		products = new ArrayList<Product>();

		limit = 2;
		// Initialize 4 package product
		// "0" - Leg; "1" - Head; "2" - Back; "3" - Neck;
		for (int i = 0; i < 4; i++) {
			packageProduct.add(new Package(incCounter(), null, limit));
		}
		halfAnimalProduct.add(new HalfAnimal(incCounter(), null));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/slaughter_test", "root", "");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.printf(e.getMessage());
		}
	}

	private int incCounter() {
		counter++;
		return counter;
	}

	public void makeProducts(Tray tray) {
		ArrayList<Part> parts = tray.getParts();
		while (parts.size() > 0) {
			switch (parts.get(0).getClass().getSimpleName()) {
			case "Leg":
				createProduct(0, parts, tray);
				break;
			case "Head":
				createProduct(1, parts, tray);
				break;
			case "Back":
				createProduct(2, parts, tray);
				break;
			case "Neck":
				createProduct(3, parts, tray);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 
	 * @param partType
	 *            "0" - Leg; "1" - Head; "2" - Back; "3" - Neck;
	 */
	public void createProduct(int partType, ArrayList<Part> parts, Tray tray) {

		
		if (!packageProduct.get(partType).isReady()) {
			packageProduct.get(partType).addPart(parts.get(0));
			parts.remove(0);
		} else {
			// package is done
			// ship it to database, then remove it and make a new one
			packageProduct.get(partType).setTray(tray);
			uploadData(packageProduct.get(partType));
			System.out.println("PACKAGE FINISHED: " + packageProduct.get(partType).toString());
			packageProduct.get(partType).removeAllParts();
			packageProduct.get(partType).setID(incCounter());
			System.out.println("NEW PACKAGE: " + packageProduct.get(partType).toString());
			//System.out.println(packageProduct.get(partType).getID());
			if(partType == 0 && !halfAnimalProduct.get(0).getHasParts()[0])
			{
				//add a leg
				halfAnimalProduct.get(0).addPart(parts.get(0));
				parts.remove(0);
			}
			else if(partType == 2 && !halfAnimalProduct.get(0).getHasParts()[1])
			{
				//add a back
				halfAnimalProduct.get(0).addPart(parts.get(0));
				parts.remove(0);
			}
			else if (halfAnimalProduct.get(0).isReady()) {
				// halfAnimal product is done
				// ship it to database, remove parts and increase counter
				halfAnimalProduct.get(0).setTray(tray);
				uploadData(halfAnimalProduct.get(0));
				System.out.println("HALF FINISHED: " + halfAnimalProduct.get(0).toString() + "; HASPARTS: " + halfAnimalProduct.get(0).getHasParts()[0]);
				halfAnimalProduct.get(0).removeAllParts();
				halfAnimalProduct.get(0).setID(incCounter());
				System.out.println("NEW HALF: " + halfAnimalProduct.get(0).toString());
			}
		}
		printAllPackages();
	}
	
	public void printAllPackages()
	{
		System.out.println("PACKAGE 0: " + packageProduct.get(0).toString());
		System.out.println("PACKAGE 1: " + packageProduct.get(1).toString());
		System.out.println("PACKAGE 2: " + packageProduct.get(2).toString());
		System.out.println("PACKAGE 3: " + packageProduct.get(3).toString());
		System.out.println("HALF PACKAGE: " + halfAnimalProduct.get(0).toString() + "\n");
	}

	private boolean added(Animal animal) {
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).compare(animal))
				return true;
		}
		return false;
	}
	
	private boolean addedProducts(Product product)
	{
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).isEqual(product))
				return true;
		}
		return false;
	}

	private void uploadData(Product product) {
		String query;
		ArrayList<Part> list = product.getParts();
		System.out.println("PRODUCT ID: " + product.toString());
		query = "INSERT INTO product (id) VALUES (" + product.getID() + ");";
		try {
			st.execute(query);
			for(int i = 0; i < list.size(); i++)
			{
				query = "INSERT INTO part (p_id, id, type, a_id, tr_id) VALUES (" + product.getID() + ", "
			+ list.get(i).getID() + ", " + list.get(i).getPartType() + ", " + list.get(i).getAnimal().getID() + ", " + product.getTray().getID() + ");";
				st.execute(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception {
		java.rmi.registry.LocateRegistry.createRegistry(1099);
		ProductController controller = new ProductController();
		Naming.rebind("Tier3_server", controller);
		System.out.println("Tier3 Bounded");
	}
}
