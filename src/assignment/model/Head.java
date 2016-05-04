package assignment.model;

public class Head extends Part
{

   public Head(int ID, double weight, Animal animal)
   {
	      setID(ID);
	      setWeight(weight);
	      setAnimal(animal);
   }

@Override
public int getPartType() {
	return 1;
}

}
