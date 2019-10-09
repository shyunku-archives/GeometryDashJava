package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Managers.ImageManager;
import Map.Core.MapObjectImage;
import Map.Engines.Drawable;

public class Spike extends Drawable{
	public Spike(int id) {
		super(MapObjectImage.TYPE_SPIKE, id);
	}

	@Override
	public void draw(Graphics2D g, double zoomRate) {
		Point p = this.getRealCoordinate(pos, zoomRate);
		f.drawGameObjectImage(g, type, id, p.x, p.y);
	}
}