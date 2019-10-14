package Map.Objects;

import java.awt.Graphics2D;
import java.awt.Point;

import Map.Core.MapObjectImage;
import Map.Engines.Drawable;
import Objects.DoubleCoordinate;

public class Decoration extends Drawable{
	public Decoration(int id, DoubleCoordinate editorPos) {
		super(MapObjectImage.TYPE_DECORATION, id, 1, editorPos);
	}

}
