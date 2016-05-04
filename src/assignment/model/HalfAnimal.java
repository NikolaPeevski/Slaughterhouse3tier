package assignment.model;

public class HalfAnimal extends Product {

	private boolean[] hasParts;
	private int legCounter;

	public HalfAnimal(int id, Tray tray) {
		super(id, tray);
		hasParts = new boolean[2];
		for(int i = 0; i < hasParts.length; i++)
		{
			hasParts[i] = false;
		}
		legCounter = 0;
	}

	@Override
	public boolean isReady() {
		for(int i = 0;  i < super.getParts().size(); i++)
		{
			Part p = super.getParts().get(i);
			switch (p.getClass().getSimpleName()) {
			case "Leg":
				if (legCounter == 2) {
					hasParts[0] = true;
				}
				break;
			case "Back":
				hasParts[1] = true;
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < hasParts.length; i++) {
			if (!hasParts[i])
				return false;
		}
		return true;
	}

	public boolean[] getHasParts() {
		return hasParts;
	}

	@Override
	public void addPart(Part part) {
		switch (part.getClass().getSimpleName()) {
		case "Leg":
			legCounter++;
			if (legCounter == 2) {
				hasParts[0] = true;
				//legCounter = 0;
			}
			super.getParts().add(part);
			break;
		case "Head":
			super.getParts().add(part);
			break;
		case "Back":
			super.getParts().add(part);
			hasParts[1] = true;
			break;
		case "Neck":
			super.getParts().add(part);
			break;
		default:
			break;
		}
	}

	@Override
	public void removeAllParts() {
		super.getParts().clear();
		hasParts[0] = false;
		hasParts[1] = false;
		legCounter = 0;
	}
}