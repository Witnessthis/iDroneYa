package iDrone;

import java.util.Observable;

import com.sun.org.apache.regexp.internal.recompile;

import de.yadrone.base.*;
import de.yadrone.base.navdata.AcceleroListener;
import de.yadrone.base.navdata.AcceleroPhysData;
import de.yadrone.base.navdata.AcceleroRawData;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.ControlState;
import de.yadrone.base.navdata.DroneState;
import de.yadrone.base.navdata.MagnetoData;
import de.yadrone.base.navdata.MagnetoListener;
import de.yadrone.base.navdata.StateListener;
import de.yadrone.base.navdata.VelocityListener;

public class DroneData extends Observable implements AltitudeListener, VelocityListener, MagnetoListener {
	public ARDrone drone;

	public final int maxAltitude = 1500;

	private strategy_e currentStrategy;

	public enum strategy_e {
		TEST_STRATEGY, POSITION_TEST, MANUAL_CONTROL, EMERGENCY, COURIER
	}

	public boolean strategyChanged = false;

	private volatile DroneState droneState = null;
	private boolean isFlying = false; //TODO start using the internal state of the drone
	private volatile int currentAltitude = 0;

	// test fields
	private boolean targetFound = false;
	private int missiles = 18;
	
	//position fields
	/* approx position is measured from an initial position of 0;0
	and a direction which defines the direction of the x-axis */
	private MagnetoData magnetoData; //a null value indicates that the initialHeading is no longer correct
	private double x;
	private double y;
	private float initialHeading;
	private float vx;
	private float vy;
	private long time;
	
	public DroneData() {
		resetPositionalData();
		
		drone = new ARDrone();
		drone.reset();
		drone.start();
		drone.reset();

		//We sometimes need to register as a listener twice before YADrone starts notifying as expected
		drone.getNavDataManager().addAltitudeListener(this);
		drone.getNavDataManager().addVelocityListener(this);
		drone.getNavDataManager().addMagnetoListener(this);
		
		drone.getNavDataManager().addAltitudeListener(this);
		drone.getNavDataManager().addVelocityListener(this);
		drone.getNavDataManager().addMagnetoListener(this);
		
		drone.getNavDataManager().removeAltitudeListener(this);
		drone.getNavDataManager().removeVelocityListener(this);
		drone.getNavDataManager().removeMagnetoListener(this);

		setStrategy(strategy_e.POSITION_TEST);
	}
	
	private void resetPositionalData(){
		magnetoData = null;
		initialHeading = 0;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;		
		time = System.currentTimeMillis();
	}

	private void notifyModelChanged() {
		setChanged();
		notifyObservers();
	}

	public synchronized strategy_e getStrategy() {
		return currentStrategy;
	}

	public synchronized void setStrategy(strategy_e strategy) {
		if (currentStrategy == strategy_e.EMERGENCY) {
			// do not let program leave emergency state
			return;
		}

		currentStrategy = strategy;
		strategyChanged = true;

		notifyModelChanged();
	}

	public synchronized boolean isFlying() {
		return isFlying;
	}

	public synchronized void setFlying(boolean isFlying) {
		this.isFlying = isFlying;
		notifyModelChanged();
	}

	// test methods
	public synchronized boolean hasTarget() {
		return targetFound;
	}

	public synchronized void setHasTarget(boolean targetFound) {
		this.targetFound = targetFound;
		notifyModelChanged();
	}

	public synchronized int getMissiles() {
		return missiles;
	}

	public synchronized void fireMissile() {
		if (missiles > 0) {
			missiles--;
		}
		notifyModelChanged();
	}

	// old state
	private state_e state = state_e.DEFAULT;

	public enum state_e {
		START, STOP, LIFT_OFF, HOVER, SPIN_RIGHT, SPIN_LEFT, LAND, STATE_SHIFT, EMERGENCY, DEFAULT, FORWARD, BACKWARD
	}

	public synchronized state_e getState() {
		return this.state;
	}

	public synchronized void setState(state_e state) {
		this.state = state;
	}

	public DroneState getDroneState() {
		return droneState;
	}

	@Override
	public void receivedAltitude(int alt) {
		currentAltitude = alt;
		notifyModelChanged();
	}

	@Override
	public void receivedExtendedAltitude(Altitude arg0) {
		// TODO Auto-generated method stub

	}

	public int getCurrentAltitude() {
		return currentAltitude;
	}

	@Override
	public void received(MagnetoData md) {
		if(magnetoData == null){//positinal data has been reset
			initialHeading = md.getHeadingFusionUnwrapped();
		}
		
		magnetoData = md;
		
		notifyModelChanged();
	}

	@Override
	public void velocityChanged(float x, float y, float z) {
		long newTime = System.currentTimeMillis();
		long timeFlown = newTime - time;
		time = newTime;
		
		float oldVx = vx;
		vx = x;
		
		float oldVy = vy;
		vy = y;
		
		//recalculate approximate position using old velocity
		//the initial heading is required 
		if(magnetoData != null){
			recalculatePosition(oldVx, oldVy, magnetoData.getHeadingFusionUnwrapped() - initialHeading, timeFlown);
		}

		notifyModelChanged();
	}
	
	//
	//Position methods
	//
	
	//assume vx is velocity in forward direction ie initialHeading
	private void recalculatePosition(double vx, double vy, double angle, double t){
		double sinA = Math.sin(angle);
		double cosA = Math.cos(angle);
		
		x += (vx * cosA + vy * sinA) * t;
		y += (vy * cosA + vx * sinA) * t;
	}
	
	//angle in degrees
	public double angleFromInitialPos(){
		//sinus relationen
		return Math.asin(y / distFromInitialPos()) * (360.0 / 2.0*Math.PI);
	}

	public double distFromInitialPos(){
		return Math.sqrt((x * x) + (y * y));
	}
	
	public double getXPos() {
		return x;
	}

	public double getYPos() {
		return y;
	}
	
	public MagnetoData getMagnetoData() {
		return magnetoData;
	}
}
