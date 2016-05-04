package assignment.controller.tier2;

import assignment.model.Animal;
import assignment.model.Part;

public interface PartFactory_int {
	Part[] makePart(int id, Animal animal);
}
