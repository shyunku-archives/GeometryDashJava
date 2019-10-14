package Game;

import Map.Core.GameEnvironment;

public class GameEngine {
	private double playerX;
	private double playerY;
	private double speed;
	
	private double gravity;
	private double proceedRate;
	
	public GameEngine(GameEnvironment env) {
		playerX = env.getStartPosition();
		playerY = 0;
		speed = env.getSpeed();
		proceedRate = 0;
		gravity = env.getGravity();
	}

	public double getPlayerX() {
		return playerX;
	}

	public void setPlayerX(double playerX) {
		this.playerX = playerX;
	}

	public double getPlayerY() {
		return playerY;
	}

	public void setPlayerY(double playerY) {
		this.playerY = playerY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getProceedRate() {
		return proceedRate;
	}

	public void setProceedRate(double proceedRate) {
		this.proceedRate = proceedRate;
	}
	
}
