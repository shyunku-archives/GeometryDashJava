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
	protected BufferedImage image;
	protected int id;
	protected int type;
	protected DoubleCoordinate pos;
	protected int rotateAngle;
	
	protected Functions f = new Functions();
	
	public abstract void draw(Graphics2D g, double zoomRate);
	
	public Drawable(int type, int id) {
		this.id = id;
		this.type = type;
		this.image = ManagerManager.im.getGameObjectImage(type, id);
		this.pos = new DoubleCoordinate(0, 0);
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
}
