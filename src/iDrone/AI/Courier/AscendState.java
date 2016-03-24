package iDrone.AI.Courier;

import de.yadrone.base.navdata.DroneState;
import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;

public class AscendState extends State{

	public AscendState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		//System.out.println("start test " + model.isFlying());
		
		if(model.getCurrentAltitude()> model.maxAltitude-100){
			return Courier.state_e.SEARCH_STATE.ordinal();
		}

		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		
		System.out.println("Ascending");

		up(ait, 1000);
	}

}
