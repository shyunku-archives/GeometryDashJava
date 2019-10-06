package Objects.Map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Drawable {
	protected BufferedImage image;
	protected Point curPos;
	public abstract void draw(Graphics2D g);
	
	public Drawable(int ID) {
		
	}
	
	public Dimension getSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}
}
