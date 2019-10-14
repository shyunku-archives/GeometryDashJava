package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Managers.ImageManager;
import Map.Core.MapObjectImage;
import Map.Engines.Drawable;
import Objects.DoubleCoordinate;

//�÷��̾�� �������� �ִ� ������Ʈ��
public class Spike extends Drawable{
	public Spike(int id, DoubleCoordinate editorPos) {
		super(MapObjectImage.TYPE_SPIKE, id, 90, editorPos);
	}
}