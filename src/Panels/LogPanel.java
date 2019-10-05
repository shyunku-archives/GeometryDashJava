package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Constants.Global;
import Managers.SoundManager;

public class LogPanel extends JPanel{
	Functions f = new Functions();
	
	@Override
	public void paintComponent(Graphics gd) {
		if(!Global.isLogViewMode)return;
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		
		f.drawFancyString(g, new String("mouse : ("+Global.mouse.x+","+Global.mouse.y+")"), 10, 135, 30f, true);
		double fps = (double)Global.drawTick/((double)(f.getElapsedTime())/1000);
		String elapsed = String.format("%.3fs",(double)(f.getElapsedTime())/1000);
		f.drawFancyString(g, new String("tick : "+Global.drawTick), 10, 160, 30f, true);
		f.drawFancyString(g, new String("fps : "+String.format("%.2f", fps)), 10, 185, 30f, true);
		f.drawFancyString(g, new String("time : "+elapsed), 10, 210, 30f, true);
	}
	
	public LogPanel(JFrame frame) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setFocusable(false);
		this.setBackground(new Color(0,0,0,0));
	}
	
	public void finalize() {
		
	}
}
