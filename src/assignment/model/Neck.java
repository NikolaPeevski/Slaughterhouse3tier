package assignment.model;

public class Neck extends Part
{

   public Neck(int ID, double weight, Animal animal)
   {
      setID(ID);
      setWeight(weight);
      setAnimal(animal);
   }

@Override
public int getPartType() {
	return 3;
}

}
