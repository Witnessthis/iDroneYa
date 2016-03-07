package iDrone.AI;

public abstract class FiniteStateMachine {	
	
	private State[] states;
	private volatile int currentState;
	private FSMThread ait;
	
	public FiniteStateMachine(State[] states){
		this.states = states;
		currentState = 0;
		
		ait = new FSMThread();
		ait.start();
	}

	public void shutDown() {
		states[currentState].awaitTransition();
		ait.running = false;
		ait.interrupt();		
	}

	public void update() {
		int nextState = states[currentState].nextTransition();
		
		System.out.println("new state = " + nextState);
		
		if(nextState >= 0){
			System.out.println("new state = " + nextState);
			
			//stop current state
			states[currentState].awaitTransition();
			ait.interrupt();
			
			//reset state before it can be reached by thread
			states[nextState].resetState();
			currentState = nextState;
			
			//recursively transition to next state if possible
			update();
		}
	}
	
	private class FSMThread extends Thread implements IFSMThread{
		private volatile boolean running = true;
		
		@Override
		public void run(){
			while(running){
				//currentState may change during execution of loop
				int state = currentState; 
				
				if(states[state].mayAct()){
					states[state].act(this);
				}
			}
		}

		@Override
		public boolean AIwait(int t) {
			if(!running || interrupted()){
				System.out.println("was interrupted");
				return true;
			}
			
			if(t > 0){
				try {
					Thread.sleep(t);
				} catch (InterruptedException e) {
					//reset interrupt flag
					interrupted();
				}
			}
						
			return false;
		}

		@Override
		public boolean isShuttingDown() {
			return !running;
		}

	}
}
