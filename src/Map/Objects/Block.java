package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;

//플레이어에게 데미지는 안주지만 x축 충돌 시 파괴됨
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
