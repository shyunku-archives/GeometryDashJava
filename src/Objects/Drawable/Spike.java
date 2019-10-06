package Objects.Drawable;

import java.awt.Graphics2D;

import Managers.ImageManager;
import Objects.Map.Drawable;

public class Spike extends Drawable{
	public Spike(int ID) {
		super(ID);
		image = ImageManager.getGameObjectbyID("Spike", ID);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}