package iDrone.AI.Test;

import iDrone.AI.*;

public class KillState extends State{

	public KillState() {
		super(new AIThread() {
			
			@Override
			public void act() {
				System.out.println("Firing Missiles");
			}
		});
	}

	@Override
	public int nextTransition() {
		// TODO Auto-generated method stub
		return 0;
	}

}
