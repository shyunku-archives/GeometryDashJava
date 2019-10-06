package Objects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Objects.Map.Drawable;

public class Layer extends ArrayList<Drawable>{
	public void draw(Graphics2D g) {
		Iterator<Drawable> i = iterator();
		while(i.hasNext()) {
			Drawable d = i.next();
			d.draw(g);
		}
	}
}
