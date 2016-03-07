package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;

public class FinnishState extends State{

	
	public FinnishState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("Finnish State");
		
		awaitTransition();
	}

}
