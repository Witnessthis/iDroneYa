package iDrone.AI;

public abstract class State {
	AIThread stateAI;
	
	public State(AIThread stateAI){
		this.stateAI = stateAI;
	}
	
	public void shutDown() {		
		stateAI.running = false;
		stateAI.interrupt();
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
		stateAI.running = true;
		stateAI.start();
	}
}
