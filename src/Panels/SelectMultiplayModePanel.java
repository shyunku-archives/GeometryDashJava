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
import Managers.ErrorManager;
import Managers.ImageManager;
import Objects.ErrorMessager;
import Objects.TriggeredButton;
import Objects.TriggeredButtonListener;
import Objects.TriggeredTextArea;

public class SelectMultiplayModePanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton goBackBtn;
	TriggeredButton participateBtn, createBtn;
	TriggeredTextArea ipTextArea, pwTextArea, nicknameTextArea;
	ErrorMessager errorMsg = new ErrorMessager();
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		f.setStartTick();
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255), 0, getSize().height, new Color(0, 69, 181), true);
		Paint original = g.getPaint();
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setPaint(original);
		
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_RIGHT_LOWER_TILE, getSize().width - 195, 549);
		
		String areaText;
		
		g.setColor(new Color(0,55, 163));
		g.fillRoundRect(150, 160, 650, 50, 30, 30);
		areaText = ipTextArea.getText();
		f.drawFancyString(g, areaText, 170, 170, 30f, new Color(200, 200, 200));
		
		g.setColor(new Color(0,55, 163));
		g.fillRoundRect(150, 280, 650, 50, 30, 30);
		areaText = nicknameTextArea.getText();
		f.drawFancyString(g, areaText, 170, 290, 30f, new Color(200, 200, 200));
		
		g.setColor(new Color(0,55, 163));
		g.fillRoundRect(150, 400, 650, 50, 30, 30);
		areaText = pwTextArea.getText();
		f.drawFancyString(g, areaText, 170, 410, 30f, new Color(200, 200, 200));
		//f.drawFancyString(g, curMap.getMapName(), getSize().width/2 - f.getFontWidth(g, curMap.getMapName(), 60f)/2, 55, 65f, Color.WHITE);
		
		f.drawFancyString(g, "Join/Create Room", 150, 20, 60f, Color.ORANGE);

		f.drawFancyString(g, "IP address (Participants Only)", 150, 120, 30f, Color.ORANGE);
		f.drawFancyString(g, "Password", 150, 360, 30f, Color.ORANGE);
		f.drawFancyString(g, "Nickname", 150, 240, 30f, Color.GREEN);
		
		f.drawFancyString(g, "Join Server", 1000, 195, 30f);
		f.drawFancyString(g, "Create Server", 1000, 325, 30f);
		
		
		f.drawFancyString(g, errorMsg.getStateMsg(), getSize().width/2 - errorMsg.getStringWidth(g, 30f)/2, 550, 30f, Color.RED);
		
		
		goBackBtn.draw(g);
		createBtn.draw(g);
		participateBtn.draw(g);
		
		f.setEndTick();
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
		
		ipTextArea = new TriggeredTextArea(new Rectangle(150, 160, 650, 50), 15, false);
		nicknameTextArea = new TriggeredTextArea(new Rectangle(150, 280, 650, 50),12, false);
		pwTextArea = new TriggeredTextArea(new Rectangle(150, 400, 650, 50), 20, false);
		this.add(ipTextArea);
		this.add(pwTextArea);
		this.add(nicknameTextArea);
		
		participateBtn = new TriggeredButton(new Rectangle(900, 170, 80, 80), ImageManager.SERVER_PARTICIPATE_BUTTON);
		participateBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				if(ipTextArea.getText().length()==0) {
					errorMsg.setError(ErrorManager.IP_ADDRESS_EMPTY_ERROR);
					return;
				}
				if(!f.isValidIPAddress(ipTextArea.getText())) {
					errorMsg.setError(ErrorManager.IP_ADDRESS_WRONG_FORMAT);
					return;
				}
				String str = nicknameTextArea.getText();
				if(str.length()==0)
					str = f.generateRandomNickname();
				ManagerManager.pm.GoToWaitingRoomPanel(false, ipTextArea.getText(), pwTextArea.getText(), str);
			}
			
		});
		this.add(participateBtn);
		
		createBtn = new TriggeredButton(new Rectangle(900, 300, 80, 80), ImageManager.SERVER_CREATE_BUTTON);
		createBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				String str = nicknameTextArea.getText();
				if(str.length()==0)
					str = f.generateRandomNickname();
				ManagerManager.pm.GoToWaitingRoomPanel(true, null, pwTextArea.getText(), str);
			}
			
		});
		this.add(createBtn);
		
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 80, 80), ImageManager.GO_BACK_PINK_BUTTON);
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
