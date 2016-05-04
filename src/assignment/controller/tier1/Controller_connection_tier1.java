package assignment.controller.tier1;

import java.io.Serializable;
import java.rmi.Naming;
import java.util.Scanner;

import assignment.controller.tier2.Controller_Tier2_int;
import assignment.model.Animal;

public class Controller_connection_tier1 implements Controller_Connection_int ,Serializable {
	private Scanner in;
	private int counter;
	private double weight;

	public Controller_connection_tier1() {
		in = new Scanner(System.in);
		counter = 0;
	}

	public void run() throws Exception {
		Controller_Tier2_int clientController = (Controller_Tier2_int) Naming
				.lookup("rmi://localhost:1099/Tier2_server");
		while (true) {
			System.out.println("Enter weight for animal " + counter + " :");
			weight = in.nextDouble();
			Animal ani = new Animal(incCounter(), weight);
			clientController.ProccessAnimal(ani);
		}
	}

	private int incCounter() {
		return counter++;
	}

	public static void main(String[] args) {
		Controller_connection_tier1 con = new Controller_connection_tier1();
		try {
			con.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
