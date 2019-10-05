package Panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class OtherModeSelectPanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton createMapButton;
	TriggeredButton goBackBtn;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255), 0, getSize().height, new Color(0, 69, 181), true);
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_UPPER_TILE, 0, 0);
		this.createMapButton.draw(g, 350, 60);
		
		goBackBtn.draw(g, 20, 15);
	}
	
	public OtherModeSelectPanel(JFrame frame) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 80, 80), ImageManager.GO_BACK_PINK_BUTTON);
		goBackBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToMainPanel();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
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
		this.add(goBackBtn);
		
		createMapButton = new TriggeredButton(new Rectangle(350, 60, 180, 180), ImageManager.CREATE_MAP_BUTTON);
		createMapButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToCreatedMapListPanel();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
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
		this.add(createMapButton);
	}
	
	@Override
	public void finalize() {
		
	}
}
