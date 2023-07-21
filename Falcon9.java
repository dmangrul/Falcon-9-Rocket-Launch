//Falcon9.java
//Deep M.            ITCS
import java.awt.Color;

public class Falcon9 extends Rocket {

	private double mass;
	private double gravity;
	private double thrust;
	private double acceleration;
	private double deltaTime;
	private double flightTime;
	private double altitude;
	private double fuelCons;
	private double fNet;
	private double time;
	private double gConstant = 6.67E-11;
	private double earthMass = 5.98E+24;
	private double r = 6.37E+6;
	private double apprH;
	
	public Falcon9(int middleX, int middleY, int aWidth, int aHeight, Color aColor, String words) {
		super(middleX, middleY, aWidth, aHeight, aColor, words);
		mass = 541300;
		gravity = gConstant*((earthMass*mass)/(Math.pow(r+altitude, 2)));
		flightTime = 0;
		thrust = 6806000;
		altitude = 0;
		fuelCons = 398900;
		deltaTime = .5;
		acceleration = 0;
		fNet = 0;
		time = 162;
		apprH = 106749;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double aMass) {
		mass = aMass;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double aGravity) {
		gravity = aGravity;
	}

	public double getThrust() {
		return thrust;
	}

	public void setThrust(double aThrust) {
		thrust = aThrust;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double anAcceleration) {
		acceleration = anAcceleration;
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}

	public double getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(double aFlightTime) {
		flightTime = aFlightTime;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double anAltitude) {
		altitude = anAltitude;
	}

	public double getFuelCons() {
		return fuelCons;
	}

	public void setFuelCons(double aFuelCons) {
		fuelCons = aFuelCons;
	}

	public double getFNet() {
		return fNet;
	}

	public void setFNet(double anFNet) {
		fNet = anFNet;
	}

	public double getVelocity() {
		return getYSpeed();
	}

	public void setVelocity(double aVelocity) {
		setYSpeed(aVelocity);
	}

	public void move(int height) {
		if (flightTime <= time) {
			mass -= (fuelCons / time) * deltaTime;
			setGravity(gConstant*((earthMass*mass)/(Math.pow(r+altitude, 2))));
			setFNet(thrust - gravity);
			acceleration = fNet / mass;
			setYSpeed(getYSpeed() + (acceleration * deltaTime));
			flightTime += deltaTime;
			altitude = (getAltitude() + getYSpeed()*deltaTime);
			setY(height * (1 - getAltitude() / apprH));
			
		}
		
	}

}
