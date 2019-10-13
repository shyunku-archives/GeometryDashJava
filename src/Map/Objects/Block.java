package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;

//�÷��̾�� �������� �������� x�� �浹 �� �ı���
public class Block extends Drawable{
	public Block(int id) {
		super(MapObjectImage.TYPE_BLOCK, id, 0);
	}

	@Override
	public void draw(Graphics2D g, double zoomRate) {
		Point p = this.getRealCoordinate(pos, zoomRate);
		f.drawGameObjectImage(g, type, id, p.x, p.y);
	}
}
