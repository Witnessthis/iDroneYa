package iDrone.AI.Test;

import iDrone.AI.*;

public class StartState extends State{
	public StartState() {
		super(new AIThread() {
			@Override
			public void act() {
				
			}
		});
	}

	@Override
	public int nextTransition() {
		return -1;
	}
}
