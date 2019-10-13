package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;

public class Decoration extends Drawable{
	public Decoration(int id) {
		super(MapObjectImage.TYPE_DECORATION, id, 1);
	}

	@Override
	public void draw(Graphics2D g, double zoomRate) {
		Point p = this.getRealCoordinate(pos, zoomRate);
		f.drawGameObjectImage(g, type, id, p.x, p.y);
	}
}
