package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;

public class JumpBall extends Drawable{
	public JumpBall(int id) {
		super(MapObjectImage.TYPE_JUMPBALL, id, 0);
	}

	@Override
	public void draw(Graphics2D g, double zoomRate) {
		Point p = this.getRealCoordinate(pos, zoomRate);
		f.drawGameObjectImage(g, type, id, p.x, p.y);
	}
}
