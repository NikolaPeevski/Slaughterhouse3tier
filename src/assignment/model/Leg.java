package assignment.model;

import java.io.Serializable;

public class Leg extends Part implements Serializable
{

   public Leg(int ID, double weight, Animal animal)
   {
	      setID(ID);
	      setWeight(weight);
	      setAnimal(animal);
   }

@Override
public int getPartType() {
	return 0;
}

}
