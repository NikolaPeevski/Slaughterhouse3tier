package assignment.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tray implements Serializable{
	private int ID;
	private double maxWeight;
	private ArrayList<Part> parts;
	private double currentWeight;

	public Tray(int ID, double maxWeight, ArrayList<Part> parts) {
		this.ID = ID;
		this.maxWeight = maxWeight;
		this.parts = parts;
		this.currentWeight = 0;
	}

	public Tray(int ID, double maxWeight) {
		this.ID = ID;
		this.maxWeight = maxWeight;
		this.parts = new ArrayList<Part>();
		this.currentWeight = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public ArrayList<Part> getParts() {
		return parts;
	}

	public void setParts(ArrayList<Part> parts) {
		this.parts = parts;
	}

	public void addPart(Part part) {
		this.parts.add(part);
		this.currentWeight += part.getWeight();
	}

	public String toString() {
		return "Tray ID: " + ID + "; Tray max weight: " + maxWeight + "; Tray parts: " + parts;
	}

	public double getCurrentWeight() {
		return this.currentWeight;
	}
	
	public void removeAllParts()
	{
		parts.clear();
		currentWeight = 0;
	}
}
