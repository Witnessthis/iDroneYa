package iDrone.AI;

public abstract class AIThread extends Thread{
	volatile boolean running = true;
	
	@Override
	public void run(){
		while(running){
			act();
		}
	}
	
	public abstract void act();
}
