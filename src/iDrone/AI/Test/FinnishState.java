package iDrone.AI.Test;

import iDrone.AI.*;

public class FinnishState extends State{

	public FinnishState() {
		super(new AIThread(){

			@Override
			public void act() {
				System.out.println("Finnish State");
			}
			
		});
	}

	@Override
	public int nextTransition() {
		return -1;
	}

}
