package assignment.controller.tier2;

import java.io.Serializable;

import assignment.model.Animal;
import assignment.model.Back;
import assignment.model.Head;
import assignment.model.Leg;
import assignment.model.Neck;
import assignment.model.Part;

public class PartFactory implements PartFactory_int,Serializable {
	private Part[] parts;
	double w_temp;
	int id_temp;
	Animal ani_temp;

	@Override
	public Part[] makePart(int id_current, Animal animal) {
		parts = new Part[7];
		w_temp = animal.getWeight() / 4;
		id_temp = id_current;
		ani_temp = animal;
		cutMain();
		cutLegs();
		return parts;
	}

	private int incCurrent() {
		id_temp++;
		return id_temp;
	}

	private double substitueW() {
		if (ani_temp.getWeight() != 0) {
			ani_temp.setWeight(ani_temp.getWeight() - w_temp);
		}
		return w_temp;

	}

	private void cutMain() {
		parts[0] = new Head(incCurrent(), substitueW(), ani_temp);
		parts[1] = new Back(incCurrent(), substitueW(), ani_temp);
		parts[2] = new Neck(incCurrent(), substitueW(), ani_temp);
	}

	private void cutLegs() {
		double w_feet = substitueW()/4;
		for(int i = 3; i<7; i++){
			parts[i] = new Leg(incCurrent(), w_feet, ani_temp);
		}
		
	}
}
