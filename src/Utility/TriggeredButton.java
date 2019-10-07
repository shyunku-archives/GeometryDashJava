package Utility;

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
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;

public class TriggeredButton extends JButton{
	private BufferedImage image = null;
	TriggeredButtonListener listener = null;
	
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
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(listener!=null)
					listener.onClickListener();
				ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void setClickListener(TriggeredButtonListener listener) {
		this.listener = listener;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		Functions.drawImage(g, image, x, y);
	}
	
	public void draw(Graphics2D g) {
		Functions.drawImage(g, image, getBounds().x, getBounds().y);
	}
}
