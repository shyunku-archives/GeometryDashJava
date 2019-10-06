package Panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Constants.Global;
import Core.Constants.ManagerManager;
import Managers.ImageManager;
import Objects.TriggeredButton;
import Objects.TriggeredButtonListener;

public class SelectMultiplayModePanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton goBackBtn;
	TriggeredButton participateBtn, createBtn;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255), 0, getSize().height, new Color(0, 69, 181), true);
		Paint original = g.getPaint();
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setPaint(original);
		
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_RIGHT_LOWER_TILE, getSize().width - 195, 549);
		
		
		g.setColor(new Color(0,55, 163));
		g.fillRoundRect(150, 160, 650, 50, 30, 30);
		g.fillRoundRect(150, 280, 650, 50, 30, 30);
		g.fillRoundRect(150, 400, 650, 50, 30, 30);
		//f.drawFancyString(g, curMap.getMapName(), getSize().width/2 - f.getFontWidth(g, curMap.getMapName(), 60f)/2, 55, 65f, Color.WHITE);
		
		f.drawFuckingFancyString(g, "Join/Create Room", 150, 20, 60f, Color.ORANGE);
		
		f.drawFuckingFancyString(g, "IP address (Participants Only)", 150, 120, 30f, Color.ORANGE);
		f.drawFuckingFancyString(g, "Password", 150, 240, 30f, Color.ORANGE);
		f.drawFuckingFancyString(g, "Nickname", 150, 360, 30f, Color.GREEN);
		
		f.drawFuckingFancyString(g, "Join Server", 1000, 195, 30f);
		f.drawFuckingFancyString(g, "Create Server", 1000, 325, 30f);
		
		
		goBackBtn.draw(g);
		createBtn.draw(g);
		participateBtn.draw(g);
	}
	
	public SelectMultiplayModePanel(JFrame frame) {
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
		
		participateBtn = new TriggeredButton(new Rectangle(900, 170, 80, 80), ImageManager.SERVER_PARTICIPATE_BUTTON);
		participateBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToOtherModeSelectPanel();
			}
			
		});
		this.add(participateBtn);
		
		createBtn = new TriggeredButton(new Rectangle(900, 300, 80, 80), ImageManager.SERVER_CREATE_BUTTON);
		createBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToOtherModeSelectPanel();
			}
			
		});
		this.add(createBtn);
		
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 80, 80), ImageManager.GO_BACK_GREEN_BUTTON);
		goBackBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToOtherModeSelectPanel();
			}
			
		});
		this.add(goBackBtn);
	}
}
