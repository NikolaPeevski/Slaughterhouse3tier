package assignment.model;

import java.io.Serializable;

public abstract class Part implements Serializable
{
   private int ID;
   private double weight;
   private Animal animal;
   

   public int getID()
   {
      return ID;
   }

   public void setID(int iD)
   {
      ID = iD;
   }

   public double getWeight()
   {
      return weight;
   }

   public void setWeight(double weight)
   {
      this.weight = weight;
   }

   public Animal getAnimal()
   {
      return animal;
   }

   public void setAnimal(Animal animal)
   {
      this.animal = animal;
   }
   
   public String toString()
   {
	   return "Part ID: " + ID + "; Part weight: " + weight + "; From animal ID: " + animal.getID() + "Part TYPE: " + getPartType();
   }
   
   public abstract int getPartType();
}
