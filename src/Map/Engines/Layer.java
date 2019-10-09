package Map.Engines;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Layer extends ArrayList<Drawable>{
	public void draw(Graphics2D g, double zoomRate) {
		Iterator<Drawable> i = iterator();
		while(i.hasNext()) {
			Drawable d = i.next();
			d.draw(g, zoomRate);
		}
	}
}
