package iDrone.AI;

import iDrone.DroneData;

public abstract class State {
	protected AIThread stateAI;
	protected DroneData model;
	
	public State(DroneData model, AIThread stateAI){
		this.model = model;
		this.stateAI = stateAI;
	}
	
	public void shutDown() {		
		stateAI.running = false;
		stateAI.interrupt();
		
		//TODO should optimally not wait for join
		try {
			stateAI.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//should return the position of the next state
	//return a negative number if conditions for a transition is not met
	public abstract int nextTransition();

	public void startUp() {
		if(stateAI.getState() == Thread.State.NEW){
			stateAI.running = true;
			stateAI.start();
		}
		
		//TODO restart old thread
	}
}
