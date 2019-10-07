package Panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;
import Network.Engine.GameClient;
import Network.Engine.GameServer;
import Network.Engine.JsonDataGenerator;
import Network.Engine.NetworkManager;
import Network.Objects.Chat;
import Network.Objects.Player;
import Network.Objects.WaitingRoomInfo;
import Utility.TriggeredButton;
import Utility.TriggeredButtonListener;
import Utility.TriggeredTextArea;
import Utility.VirtualScroller;

public class WaitingRoomPanel extends JPanel{
	Functions f = new Functions();
	TriggeredButton goBackBtn;
	private GameClient client = null;
	private GameServer server = null;
	TriggeredTextArea chatTextArea;
	private String myName;
	
	public static VirtualScroller vscroll = new VirtualScroller();
	private long start, end;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		f.setStartTick();
		
		WaitingRoomInfo roomInfo = client.getWaitingRoomInfo();
		ArrayList<Player> players = roomInfo.getPlayers();
		ArrayList<Chat> chats = roomInfo.getChats();
		vscroll.setThis(-chats.size()+11, 0);
		
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
			Color c = Color.WHITE;
			if(cur.getPlayerNickname().equals(myName))c = new Color(100, 250, 100);
			f.drawFancyString(g, cur.getPlayerNickname(), 190, 170 + 50*i, 30f, c);
			f.drawFancyString(g, cur.getIpAddress(), 190 + f.getFontWidth(g, cur.getPlayerNickname(), 30f) + 10, 182 + 50*i, 15f,
					cur.getPlayerNickname().equals(roomInfo.getRoomMasterName())?Color.YELLOW:Color.WHITE);
			g.drawLine(179, 158 + 50*(i+1), 590, 158 + 50*(i+1));
		}
		
		
		
		g.setColor(new Color(120, 78, 40));
		g.fillRect(720, 150, 420, 275);
		
		g.setColor(new Color(80, 50, 23));
		g.fillRect(720, 395, 420, 30);
		
		Shape save = g.getClip();
		g.setClip(729, 158, 411, 232);
		
		int scroll = vscroll.getCoordinate(20);
		for(int i=0;i<chats.size();i++) {
			Chat cur = chats.get(i);
			int cre = 380-chats.size()*20+ i*20 - scroll;
			if(cre < 110)continue;
			Color c = Color.WHITE;
			if(cur.isSystemic())c = new Color(255, 40, 255);
			else if(cur.getSender().equals(myName))c = new Color(100, 250, 100);
			f.drawFancyString(g, cur.toMessage(), 740, cre, 18f, c);
		}
		g.setClip(save);
		
		f.drawFancyString(g, chatTextArea.getText(), 737, 400, 20f);
		
		f.drawImage(g, f.resizeImage(ImageManager.LEFT_SIDE_BAR, 15, 465), 165, 155);
		f.drawImage(g, f.resizeImage(ImageManager.RIGHT_SIDE_BAR, 15, 465), 590, 155);
		
		f.drawImage(g, ImageManager.WAITING_ROOM_PLAYER_LIST_UPPER_BAR, 150, 120);
		f.drawImage(g, ImageManager.WAITING_ROOM_PLAYER_LIST_LOWER_BAR, 152, 620);
		
		f.drawImage(g, f.resizeImage(ImageManager.WAITING_ROOM_CHAT_MIDDLE_BAR, 415, 10), 730, 390);
		
		f.drawImage(g, f.resizeImage(ImageManager.LEFT_SIDE_BAR, 15, 265), 715, 155);
		f.drawImage(g, f.resizeImage(ImageManager.RIGHT_SIDE_BAR, 15, 265), 1140, 155);
		
		f.drawImage(g, ImageManager.WAITING_ROOM_CHAT_UPPER_BAR, 700, 120);
		f.drawImage(g, ImageManager.WAITING_ROOM_CHAT_LOWER_BAR, 702, 420);
		
		
		
		f.drawFancyString(g, roomInfo.getRoomName(), 150, 20, 50f, Color.ORANGE);
		f.drawFancyString(g, "Room IP : "+roomInfo.getRoomIP(), 150, 70, 20f, Color.WHITE);
		String partic = "Participants "+String.format("(%d/%d)", players.size(), NetworkManager.MAX_CAPACITY);
		f.drawFancyString(g, partic, 384 - f.getFontWidth(g, partic, 25f)/2, 125, 25f, Color.WHITE);
		partic = "Chats "+String.format("(%d)", chats.size());
		f.drawFancyString(g, partic, 934 - f.getFontWidth(g, partic, 25f)/2, 125, 25f, Color.WHITE);
		
		goBackBtn.draw(g);
		
		f.setEndTick();
	}
	
	//Participate
	public WaitingRoomPanel(JFrame frame, String IPAddress, String password, String nickname) {
		init(frame);
		myName = nickname;
		client = new GameClient(new Player(nickname, f.getVeiledPublicIPofMine()));
		client.connect(IPAddress, password);
	}
	
	//Create
	public WaitingRoomPanel(JFrame frame, String password, String nickname) {
		init(frame);
		myName = nickname;
		server = new GameServer(new WaitingRoomInfo(nickname+"'s Room", f.getMyPublicIPAddress(), password, nickname));
		server.start();
		client = new GameClient(new Player(nickname, f.getVeiledPublicIPofMine()));
		client.connect(NetworkManager.LOCALHOST, password);
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
		
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				vscroll.scroll(e.getWheelRotation());
			}
			
		});
		
		chatTextArea = new TriggeredTextArea(new Rectangle(730, 390, 410, 30), 30, true);
		chatTextArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					JsonDataGenerator jgen = new JsonDataGenerator();
					String message = chatTextArea.getText();
					if(message.length()!=0) {
						client.sendMessageToServer(jgen.bindAll(NetworkManager.NORMAL_CHAT_SEND, myName, new Chat(myName, message, false).getJson()));
						chatTextArea.setText("");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.add(chatTextArea);
		
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 80, 80), ImageManager.GO_BACK_PINK_BUTTON);
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
