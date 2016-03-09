
//
// DEPRECATED!!!!!!
//


package iDrone.AI;

import iDrone.DroneData;
import iDrone.DroneData.state_e;

public class AI extends Thread {
	DroneData model;
	DroneController droneControl;
	boolean running = true;

	public AI(DroneData model) {
		this.model = model;
	}

	@Override
	public void run() {

		//DroneData.state_e last_state = DroneData.state_e.DEFAULT;
		DroneData.state_e currentState;
		
		while (true) {

			currentState = model.getState();
			
			switch (currentState) {

			case DEFAULT:
				// DO NOTHING
				break;

			case START:
				model.drone.start();
				model.drone.reset();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case LIFT_OFF:
				model.drone.takeOff();
				model.setState(DroneData.state_e.HOVER);
				break;

			case HOVER:
				model.drone.hover();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case SPIN_LEFT:
				model.drone.spinLeft();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case SPIN_RIGHT:
				model.drone.spinRight();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case LAND:
				model.drone.landing();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case STOP:
				model.drone.stop();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case STATE_SHIFT:
				model.drone.hover();
				model.setState(DroneData.state_e.DEFAULT);
				break;

			case EMERGENCY:
				model.drone.hover();

				try {
					currentThread();
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				model.drone.landing();
				model.setState(DroneData.state_e.DEFAULT);

				break;

			default:
				// SHOULD NEVER GET HERE
				break;

			}

		}
		// System.out.println("start");
		// //model.drone.reset();
		// model.drone.takeOff();
		// System.out.println("hover");
		// model.drone.hover();
		//
		// try {
		// currentThread();
		// Thread.sleep(8000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// while(running){
		// if(!model.hasTarget()){
		// lookForTarget();
		// }
		//
		//
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// running = false;
		// }
		//
		// model.drone.landing();
		//
		// try {
		// currentThread();
		// Thread.sleep(4000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// model.drone.stop();

	}

	private void lookForTarget() {
		model.drone.spinRight();
	}
}
