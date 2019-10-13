package Utility;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import Core.Functions;
import Managers.ImageManager;
import Managers.ManagerManager;

public class FocusableRadioButton extends JButton{
	public BufferedImage focusedImage, unfocusedImage;
	public Point pos;
	private boolean isFocused;
	private TriggeredButtonListener listener = null;
	
	public FocusableRadioButton(int focusedImg, int unfocusedImg, Point p) {
		this.focusedImage = Functions.getImage(focusedImg);
		if(unfocusedImg == -1)
			this.unfocusedImage = Functions.dye(Functions.getImage(focusedImg), new Color(0,0,0,80));
		else
			this.unfocusedImage = Functions.getImage(unfocusedImg);
		
		init(p, true);
	}
	
	public FocusableRadioButton(Point p, int w, int h) {
		this.unfocusedImage = Functions.resizeImage(Functions.getImage(ImageManager.BLANK),w,h);
		this.focusedImage = Functions.dye(w, h, new Color(5,20,40,180));
		init(p, false);
	}
	
	private void init(Point p, boolean cursorChange) {
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
				if(cursorChange)
					ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(cursorChange)
					ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(cursorChange)
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
