package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;
import Objects.DoubleCoordinate;

public class JumpBall extends Drawable{
	public JumpBall(int id, DoubleCoordinate editorPos) {
		super(MapObjectImage.TYPE_JUMPBALL, id, 0, editorPos);
	}
}
