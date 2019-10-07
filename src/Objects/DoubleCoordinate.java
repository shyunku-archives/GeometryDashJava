package Objects;

public class DoubleCoordinate {
	public double x,y;
	
	public DoubleCoordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String getPosByString() {
		return String.format("(%.1f, %.1f)", x,y);
	}
}
