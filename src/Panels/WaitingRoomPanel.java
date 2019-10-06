package Panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Constants.Global;
import Core.Constants.ManagerManager;
import Managers.ImageManager;
import Network.Engine.GameClient;
import Network.Engine.GameServer;
import Network.Engine.NetworkManager;
import Network.Objects.Player;
import Network.Objects.WaitingRoomInfo;
import Objects.TriggeredButton;
import Objects.TriggeredButtonListener;

public class WaitingRoomPanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton goBackBtn;
	private GameClient client = null;
	private GameServer server = null;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		WaitingRoomInfo roomInfo = client.getWaitingRoomInfo();
		ArrayList<Player> players = roomInfo.getPlayers();
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255), 0, getSize().height, new Color(0, 69, 181), true);
		Paint original = g.getPaint();
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setPaint(original);
		
		g.setColor(new Color(191, 114, 62));
		g.fillRect(170, 150, 430, 480);
		
		f.drawCornerTileLowerRight(g, this);
		f.drawCornerTileUpperLeft(g);
		
		g.setColor(Color.BLACK);
		
		for(int i=0;i<players.size();i++) {
			Player cur = players.get(i);
			f.drawFuckingFancyString(g, cur.getPlayerNickname(), 190, 170 + 50*i, 30f, 
					roomInfo.getRoomMasterName().equals(cur.getPlayerNickname())?Color.green:Color.WHITE);
			f.drawFuckingFancyString(g, cur.getIpAddress(), 190 + f.getFontWidth(g, cur.getPlayerNickname(), 30f) + 10, 182 + 50*i, 15f);
			g.drawLine(179, 158 + 50*(i+1), 590, 158 + 50*(i+1));
		}
		
		
		f.drawImage(g, f.resizeImage(ImageManager.LEFT_SIDE_BAR, 15, 465), 165, 155);
		f.drawImage(g, f.resizeImage(ImageManager.RIGHT_SIDE_BAR, 15, 465), 590, 155);
		
		f.drawImage(g, ImageManager.WAITING_ROOM_UPPER_BAR, 150, 120);
		f.drawImage(g, ImageManager.WAITING_ROOM_LOWER_BAR, 152, 620);
		
		f.drawFuckingFancyString(g, roomInfo.getRoomName(), 150, 20, 50f, Color.ORANGE);
		f.drawFuckingFancyString(g, "Room IP : "+roomInfo.getRoomIP(), 150, 70, 20f, Color.WHITE);
		f.drawFuckingFancyString(g, "Participants", 384 - f.getFontWidth(g, "Participants", 25f)/2, 125, 25f, Color.WHITE);
		
		goBackBtn.draw(g);
	}
	
	//Participate
	public WaitingRoomPanel(JFrame frame, String IPAddress, String password, String nickname) {
		init(frame);
		try {
			client = new GameClient(new Player(nickname, InetAddress.getLocalHost().getHostAddress()));
			client.connect(IPAddress, password);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	//Create
	public WaitingRoomPanel(JFrame frame, String password, String nickname) {
		init(frame);
		try {
			server = new GameServer(new WaitingRoomInfo(nickname+"'s Room", InetAddress.getLocalHost().getHostAddress(), password, nickname));
			server.start();
			client = new GameClient(new Player(nickname, InetAddress.getLocalHost().getHostAddress()));
			client.connect(NetworkManager.LOCALHOST, password);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void init(JFrame frame) {
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
		

		
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 80, 80), ImageManager.GO_BACK_GREEN_BUTTON);
		goBackBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToSelectMultiplayModePanel();
			}
			
		});
		this.add(goBackBtn);
	}
}
