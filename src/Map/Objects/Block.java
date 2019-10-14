package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;
import Objects.DoubleCoordinate;

//�÷��̾�� �������� �������� x�� �浹 �� �ı���
public class Block extends Drawable{
	public Block(int id, DoubleCoordinate editorPos) {
		super(MapObjectImage.TYPE_BLOCK, id, 0, editorPos);
	}
}
