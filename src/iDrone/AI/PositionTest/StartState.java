package iDrone.AI.PositionTest;

import iDrone.DroneData;
import iDrone.AI.IFSMThread;
import iDrone.AI.State;
import iDrone.AI.Courier.Courier;

public class StartState extends State{

	public StartState(DroneData model) {
		super(model);
	}

	@Override
	public int nextTransition() {
		//System.out.println("start test " + model.isFlying());
		//DroneState droneState = model.getDroneState();
		if (model.isFlying()) {
			return PositionTestStrategy.state_e.FLYFORWARD.ordinal();
		}

		return -1;
	}

	@Override
	public void act(IFSMThread ait) {
		System.out.println("start");
		//DroneState droneState = model.getDroneState();
		model.drone.setHorizontalCamera();

		System.out.println("start test " + model.isFlying());
		if (!model.isFlying()) {
			System.out.println("start test takeOff");
			takeOff(ait, 8000);
		}

		hover(ait, 0);

		awaitTransition();
	}

}
