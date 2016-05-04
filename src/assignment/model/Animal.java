package assignment.model;

import java.io.Serializable;

public class Animal implements Serializable {
	private int ID;
	private double weight;

	public Animal(int ID, double weight) {
		this.ID = ID;
		this.weight = weight;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "Animal ID: " + ID + "; Animal weight: " + weight;
	}
	public boolean compare(Animal animal){
		if(this.ID == animal.ID)
			return true;
		return false;
	}

}
