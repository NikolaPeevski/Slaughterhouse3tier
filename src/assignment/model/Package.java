package assignment.model;

public class Package extends Product {
	private int limit;

	public Package(int id, Tray tray, int limit) {
		super(id, tray);
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Override
	public boolean isReady()
	{
		return super.getParts().size() == limit;
	}

	@Override
	public void addPart(Part part) {
		super.getParts().add(part);
	}

	@Override
	public void removeAllParts() {
		super.getParts().clear();
	}
}
