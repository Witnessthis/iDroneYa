package iDrone.AI;

import de.yadrone.base.ARDrone;
import iDrone.DroneData;

public abstract class AIThread extends Thread{
	protected volatile boolean running = true;
	protected DroneData model;
	//protected ARDrone drone;
	
	public AIThread(DroneData model){//ARDrone drone){
		this.model = model;
		//this.drone = drone;
	}
	
	@Override
	public void run(){
		while(running){
			act();
			//TODO enforce interruption checks on every action
		}
	}
	
	protected boolean AIwait(long millis){
		//don't try to sleep if thread is being shut down
		if(!running || isInterrupted()){
			return true;
		}
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			if(isInterrupted()){//probably redundant
				running = false;
				return true;
			}
		};
		
		return false;
	}
	
	protected abstract void act();

	public boolean isRunning() {
		return running;
	}
}
