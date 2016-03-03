package iDrone.AI.Test;

import iDrone.DroneData;
import iDrone.AI.*;

public class FinnishState extends State{

	public FinnishState(DroneData model) {
		super(model, new AIThread(model){

			@Override
			protected void act() {
				System.out.println("Finnish State");
				
				running = false;
			}
			
		});
	}

	@Override
	public int nextTransition() {
		return -1;
	}

}
