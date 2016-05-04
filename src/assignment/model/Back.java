package assignment.model;

public class Back extends Part
{

   public Back(int ID, double weight, Animal animal)
   {
	      setID(ID);
	      setWeight(weight);
	      setAnimal(animal);
   }

@Override
public int getPartType() {
	return 2;
}

}
