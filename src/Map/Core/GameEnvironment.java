package Map.Core;

import Network.Engine.JsonFormattable;

public class GameEnvironment extends JsonFormattable{
	private double speed;
	private double gravity;
	private double startPosition;
	//BGM
	
	public GameEnvironment() {
		super();
		this.speed = 5;
		this.gravity = 2.2;
		this.startPosition = -3;
	}
	
	public GameEnvironment(double speed, double gravity, double startPosition) {
		super();
		this.speed = speed;
		this.gravity = gravity;
		this.startPosition = startPosition;
	}
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public double getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(double startPosition) {
		this.startPosition = startPosition;
	}
}
