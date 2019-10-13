package Utility;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import Core.Functions;
import Managers.ManagerManager;

public class FocusableRadioButton extends JButton{
	public BufferedImage focusedImage, unfocusedImage;
	public Point pos;
	private boolean isFocused;
	private TriggeredButtonListener listener = null;
	
	public FocusableRadioButton(int focusedImg, int unfocusedImg, Point p) {
		this.focusedImage = Functions.getImage(focusedImg);
		this.unfocusedImage = Functions.getImage(unfocusedImg);
		this.pos = p;
		this.isFocused = false;
		
		this.setBounds(p.x,p.y, focusedImage.getWidth(), focusedImage.getHeight());
		this.setFocusable(true);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setVisible(true);
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
	
	public void focusThis() {
		isFocused = true;
	}
	
	public void unfocusThis() {
		isFocused = false;
	}
	
	public void draw(Graphics2D g) {
		Functions.drawImage(g, isFocused?focusedImage:unfocusedImage, pos.x, pos.y);
	}
	
	public void setListener(TriggeredButtonListener listener) {
		this.listener = listener;
	}
}
