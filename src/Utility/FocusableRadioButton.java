package Utility;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import Core.Functions;

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