package Panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Constants.Global;
import Core.Constants.ManagerManager;
import Managers.ImageManager;
import Objects.TriggeredButton;
import Objects.Map.Map;

public class EditMapPanel extends JPanel{
	Functions f = new Functions();
	Map curMap = null;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		
	}
	
	public EditMapPanel(JFrame frame, Map map) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		curMap = map;
	}
	
	@Override
	public void finalize() {
		
	}
}
