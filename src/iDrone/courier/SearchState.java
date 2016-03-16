package iDrone.courier;

import de.yadrone.base.command.LEDAnimation;
import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class SearchState extends State{

	public SearchState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		if(model.getCurrentAltitude()> model.maxAltitude-100){
			//return Courier.state_e.APPROACH_STATE.ordinal();
		}

		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("Ascending");

		hover(ait, 5000);
		spinLeft(ait, 5000);
		spinRight(ait, 5000);
		//model.drone.getCommandManager().setLedsAnimation(LEDAnimation.FIRE, 10, 5000);
		landing(ait, 0);
		awaitTransition();
		
	}

}
