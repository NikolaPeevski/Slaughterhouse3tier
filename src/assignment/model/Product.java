package assignment.model;

import java.util.ArrayList;

public abstract class Product {
	private int ID;
	private ArrayList<Part> parts;
	private Tray tray;
	
	public Product(int id, Tray tray)
	{
		this.ID = id;
		this.parts = new ArrayList<Part>();
		this.tray = tray;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
	
	public void setParts(ArrayList<Part> parts) {
		this.parts = parts;
	}
	
	public Tray getTray() {
		return tray;
	}

	public void setTray(Tray tray) {
		this.tray = tray;
	}

	public abstract void removeAllParts();
	
	public abstract void addPart(Part part);
	
	public abstract boolean isReady();
	
	public boolean isEqual(Product p)
	{
		return this.ID == p.ID;
	}
	
	public String toString()
	{
		return "Product ID: " + ID + "; Parts: " + getParts();
	}
}
