package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Constants.Global;

public class OfflineMapSelectPanel extends JPanel{
	Functions f = new Functions();
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		
		
	}
	
	public OfflineMapSelectPanel(JFrame frame) {
		this.setSize(frame.getSize());
		this.setVisible(true);
	}
	
	public void finalize() {
		
	}
}
