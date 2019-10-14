package Map.Engines;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Core.Functions;
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;
import Map.Core.Map;
import Objects.DoubleCoordinate;

public abstract class Drawable {
	protected transient BufferedImage image;
	protected int id;
	protected int type;
	protected int layer;
	protected DoubleCoordinate pos;
	protected int rotateAngle;
	protected double rotatePeriod;
	
	protected transient Functions f = new Functions();
	
	public void draw(Graphics2D g, double zoomRate) {
		DoubleCoordinate p = this.getPreciseRealCoordinate(pos, zoomRate);
		double rate = zoomRate;
		f.drawResizedImage(g, this.image, (int)p.x,
				(int)(p.y - image.getHeight()*rate), (int)(image.getWidth()*rate), (int)(image.getHeight()*rate));
	}
	
	public Drawable(int type, int id, double rotatePeriod, DoubleCoordinate editorPos) {
		this.id = id;
		this.type = type;
		this.image = ManagerManager.im.getGameObjectImage(type, id, 50, 50);//format
		this.layer = 0;
		this.pos = editorPos;
		this.rotateAngle = 0;
	}
	
	
	
	public Dimension getSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	public BufferedImage getImage() {
		return image;
	}

	public DoubleCoordinate getPos() {
		return pos;
	}

	public void setPos(DoubleCoordinate pos) {
		this.pos = pos;
	}

	public int getRotateAngle() {
		return rotateAngle;
	}

	public void setRotateAngle(int rotateAngle) {
		this.rotateAngle = rotateAngle;
	}
	
	public DoubleCoordinate getEditorCoordinate(Point real, double zoomRate) {
		Point origin = Global.originCoordinate;
		double dx = real.x - origin.x;
		double dy = -(real.y - origin.y);
		dx/=Map.DEFAULT_GRID_SIZE*zoomRate;
		dy/=Map.DEFAULT_GRID_SIZE*zoomRate;
		return new DoubleCoordinate(dx, dy);
	}
	
	public Point getRealCoordinate(DoubleCoordinate editPos, double zoomRate) {
		Point origin = Global.originCoordinate;
		double dx = editPos.x*Map.DEFAULT_GRID_SIZE*zoomRate;
		double dy = editPos.y*Map.DEFAULT_GRID_SIZE*zoomRate;
		dx = dx + origin.x;
		dy = origin.y - dy;
		return new Point((int)dx, (int)dy);
	}
	
	public DoubleCoordinate getPreciseRealCoordinate(DoubleCoordinate editPos, double zoomRate) {
		Point origin = Global.originCoordinate;
		double dx = editPos.x*Map.DEFAULT_GRID_SIZE*zoomRate;
		double dy = editPos.y*Map.DEFAULT_GRID_SIZE*zoomRate;
		dx = dx + origin.x;
		dy = origin.y - dy;
		return new DoubleCoordinate(dx, dy);
	}
}
