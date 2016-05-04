package assignment.controller.tier2;

import java.io.Serializable;
import java.rmi.Naming;

import assignment.controller.tier3.ProductController_int;
import assignment.model.Part;
import assignment.model.Tray;

public class TrayController implements TrayController_int, Serializable {
	private Tray legs;
	private Tray head;
	private Tray back;
	private Tray neck;
	private int counter;

	public TrayController() {
		counter = 0;
		legs = new Tray(incCounter(), 1000);
		head = new Tray(incCounter(), 1000);
		back = new Tray(incCounter(), 1000);
		neck = new Tray(incCounter(), 1000);
	}

	@Override
	public void insertIntrays(Part[] part) throws Exception {
		for (int i = 0; i < 7; i++) {
			switch (part[i].getClass().getSimpleName()) {
			case "Leg":
				addLeg(part[i]);
				break;
			case "Head":
				addHead(part[i]);
				break;
			case "Back":
				addBack(part[i]);
				break;
			case "Neck":
				addNeck(part[i]);
				break;
			default:
				break;
			}
		}
	}

	private int incCounter() {
		counter++;
		return counter;
	}

	private void addLeg(Part part) throws Exception{
		legs.addPart(part);
		checkAndSend(legs);
	}

	private void addHead(Part part) throws Exception{
		head.addPart(part);
		checkAndSend(head);
	}

	private void addNeck(Part part) throws Exception{
		neck.addPart(part);
		checkAndSend(neck);
	}

	private void addBack(Part part) throws Exception {
		back.addPart(part);
		checkAndSend(back);
	}

	private void checkAndSend(Tray tray) throws Exception{
		if (tray.getCurrentWeight() > (tray.getMaxWeight() - 200)) {
			ProductController_int clientController = (ProductController_int) Naming
					.lookup("rmi://10.52.224.39:1099/Tier3_server");
			clientController.makeProducts(tray);
			tray.removeAllParts();
		}

	}
}
