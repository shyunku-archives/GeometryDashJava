package Panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;
import Objects.TriggeredButton;

public class OtherModeSelectPanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton createMapButton, multiplayBtn;
	TriggeredButton goBackBtn;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		f.setStartTick();
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255), 0, getSize().height, new Color(0, 69, 181), true);
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_UPPER_TILE, 0, 0);
		this.createMapButton.draw(g, 350, 60);
		
		goBackBtn.draw(g, 20, 15);
		multiplayBtn.draw(g);
		
//		f.drawRefinedString(g, "Multiplay", 577, 182, 25f, new Color(80,80,80));
//		f.drawFancyString(g, "Multiplay", 575, 180, 25f);
		f.drawFuckingFancyString(g, "Multiplay", 575, 180, 25f);
		
		f.setEndTick();
	}
	
	public OtherModeSelectPanel(JFrame frame) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				Global.mouse = new Point(e.getX(), e.getY());
			}
			
		});
		
		multiplayBtn = new TriggeredButton(new Rectangle(560, 60, 180, 180), ImageManager.PARTICIPATE_MULTIPLAY_BUTTON);
		multiplayBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToSelectMultiplayModePanel();
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
		this.add(multiplayBtn);
		
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
