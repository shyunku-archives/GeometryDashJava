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
	final float fontsize = 20f;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		
		g.setColor(new Color(0,0,0,30));
		g.fillRect(getSize().width/2 - 90, getSize().height-90, 150, 40);
		f.drawFancyString(g, Global.version, getSize().width/2 - f.getFontWidth(g, Global.version, 25f)/2, getSize().height-80, 20f);
		
		if(!Global.isLogViewMode)return;		
		double fps = (double)Global.drawTick/((double)(f.getElapsedTime())/1000);
		String elapsed = String.format("%.3fs",(double)(f.getElapsedTime())/1000);
		
		int rightEnd = 1330, startY = 15, heightGap = 15;
		f.drawFuckingFancyRightAlignedString(g, new String("mouse : ("+Global.mouse.x+","+Global.mouse.y+")"), rightEnd, startY, fontsize, Color.ORANGE);
		f.drawFuckingFancyRightAlignedString(g, new String("tick : "+Global.drawTick), rightEnd, startY+heightGap*1, fontsize, Color.ORANGE);
		f.drawFuckingFancyRightAlignedString(g, new String("fps : "+String.format("%.2f", fps)), rightEnd, startY+heightGap*2, fontsize, Color.ORANGE);
		f.drawFuckingFancyRightAlignedString(g, new String("time : "+elapsed), rightEnd, startY+heightGap*3, fontsize, Color.ORANGE);
		
		f.drawFuckingFancyRightAlignedString(g, "edit pos : "+String.format("(%d, %d)", Global.editModePos.x, Global.editModePos.y), rightEnd, startY+heightGap*5, fontsize, Color.ORANGE);
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
