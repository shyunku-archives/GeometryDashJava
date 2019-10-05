package Objects;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import Core.Functions;
import Core.Constants.Global;
import Managers.ImageManager;

public class TriggeredButton extends JButton{
	private BufferedImage image = null;
	
	public TriggeredButton(Rectangle rect, int flag) {
		this.setBounds(rect.x,rect.y, rect.width, rect.height);
		this.setFocusable(true);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setVisible(true);
		this.image = Functions.getImage(flag);
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				Global.mouse = new Point(e.getX()+getX(), e.getY()+getY());
			}
			
		});
	}
	
	public void draw(Graphics2D g, int x, int y) {
		Functions.drawImage(g, image, x, y);
	}
}
