package iDrone.AI;

import iDrone.DroneData;

public abstract class State {
	//protected AIThread stateAI;
	protected DroneData model;
	
	public State(DroneData model){//, AIThread stateAI){
		this.model = model;
		resetState();
	}
	
	/*public void shutDown() {		
		stateAI.running = false;
		stateAI.interrupt();
		
		//TODO should optimally not wait for join
		try {
			stateAI.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	//should return the position of the next state
	//return a negative number if conditions for a transition is not met
	public abstract int nextTransition();
	
	public abstract void act(IFSMThread ait);
		
	private volatile boolean mayAct = true;
	protected void awaitTransition(){
		mayAct = false;
	}
	
	public boolean mayAct(){
		return mayAct;
	}
	
	public void resetState(){
		mayAct = true;
	}
	
	protected void AIwait(IFSMThread ait, int t){
		if(ait.AIwait(t)){
			//thread was interrupted
			
		}
	}
	
	//drone commands
	protected void landing(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.landing();
		model.setFlying(false);	
		
		AIwait(ait, t);
	};
	
	protected void takeOff(IFSMThread ait, int t){		
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.takeOff();
		
		AIwait(ait, t);

		model.setFlying(true);	
		System.out.println("abstract start test " + model.isFlying());
	};
	
	protected void forward(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.forward();
		
		AIwait(ait, t);
	};
	
	protected void backward(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		//model.drone.backward();
		
		AIwait(ait, t);
	};
	
	protected void spinRight(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.spinRight();
		
		AIwait(ait, t);
	};
	
	protected void spinLeft(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		//model.drone.spinLeft();
		
		AIwait(ait, t);
	};
	
	protected void up(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.up();
		
		AIwait(ait, t);
	};
	
	protected void down(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.down();
		
		AIwait(ait, t);
	};
	
	protected void goRight(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.goRight();
		
		AIwait(ait, t);
	};
	
	protected void goLeft(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.goLeft();
		
		AIwait(ait, t);
	};
	
	protected void freeze(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.freeze();
		
		AIwait(ait, t);
	};
	
	protected void hover(IFSMThread ait, int t){
		if(!mayAct || ait.isShuttingDown()){
			return;
		}
		
		model.drone.hover();
		
		AIwait(ait, t);
	};
	
}
