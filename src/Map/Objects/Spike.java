package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Managers.ImageManager;
import Map.Core.MapObjectImage;
import Map.Engines.Drawable;
import Objects.DoubleCoordinate;

//플레이어에게 데미지를 주는 오브젝트들
public class Spike extends Drawable{
	public Spike(int id, DoubleCoordinate editorPos) {
		super(MapObjectImage.TYPE_SPIKE, id, 90, editorPos);
	}
}