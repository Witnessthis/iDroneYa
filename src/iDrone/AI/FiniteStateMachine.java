package iDrone.AI;

public abstract class FiniteStateMachine {	
	
	private State[] states;
	private int currentState;
	
	public FiniteStateMachine(State[] states){
		this.states = states;
		currentState = 0;
		
		states[currentState].startUp();
	}

	public void shutDown() {
		states[currentState].shutDown();
	}

	public void update() {
		int nextState = states[currentState].nextTransition();
		
		if(nextState >= 0){
			transition(nextState);
		}
	}

	private void transition(int nextState) {
		states[currentState].shutDown();
		currentState = nextState;
		
		states[currentState].startUp();
		
		//do not start up the new state if we could transition right away
		/*
		int newState = states[nextState].nextTransition();
		if(newState < 0){//cannot transition further yet
			states[currentState].startUp();
		}
		else{
			transition(newState);
		}
		*/
		
	}
}
