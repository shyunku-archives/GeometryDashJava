package Panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Core.Constants.Dimensions;
import Managers.FontManager;
import Managers.ImageManager;
import Managers.ManagerManager;
import Managers.SoundManager;
import Utility.TriggeredButton;

public class MainPanel extends JPanel{
	Functions f = new Functions();
	private ArrayList<Rectangle> backgroundTiles = new ArrayList<>();
	private float tileMovementSpeed = 0.072f;
	private long startsec = 0;
	private int tileShadowOffset = 3;
	private TriggeredButton playOfflineBtn, otherModeSelectBtn;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		Color rw = Functions.getRainbowColor(400, f.getElapsedTime(), 0.02);
		int bgsize = 1536, gsize = 256;
		Functions.drawImage(g, ImageManager.GAME_BG_TILE,
				Functions.xAxisLoopCoord(bgsize, 2*bgsize, (int)(bgsize-f.getElapsedTime()*this.tileMovementSpeed)), 0);
		Functions.drawImage(g, ImageManager.GAME_BG_TILE,
				Functions.xAxisLoopCoord(bgsize, 2*bgsize, (int)(2*bgsize-f.getElapsedTime()*this.tileMovementSpeed)), 0);
		
		for(int i=0;i<8;i++)
			Functions.drawImage(g, ImageManager.GAME_GROUND_TILE,
					Functions.xAxisLoopCoord(gsize, 8*gsize, (int)((i+1)*gsize-f.getElapsedTime()*this.tileMovementSpeed*8)), 530);
		g.setColor(Functions.getDarkerColor(new Color(rw.getRed(), rw.getGreen(), rw.getBlue(), 130), 0.8f));
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.fillRect(0, 530, getSize().width, getSize().height-530);
		
		Functions.drawImage(g, ImageManager.MAIN_LOGO, 250, 50);
		Functions.drawImage(g, ImageManager.PLAY_OFFLINE_LEVELS_BUTTON_SHADOW, 578+10, 250+6);
		this.playOfflineBtn.draw(g, 578, 250);
		Functions.drawImage(g, ImageManager.PLAY_OTHERS_LEVELS_BUTTON_SHADOW, 855+6, 270+4);
		this.otherModeSelectBtn.draw(g, 855, 270);
		Functions.drawImage(g, ImageManager.SKIN_SETTING_BUTTON_SHADOW, 345+6, 270+4);
		Functions.drawImage(g, ImageManager.SKIN_SETTING_BUTTON, 345, 270);
		
		
		//f.drawFancyString(g, new String("Game Start"), 530, 500, 45f, false);
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,0,0,0), getSize().width/2, 0, Color.WHITE, true);
		g.setPaint(gp);
		g.setStroke(new BasicStroke(3));
		g.drawLine(0, 530, getSize().width, 530);
	}
	
	public MainPanel(JFrame frame) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		playOfflineBtn = new TriggeredButton(new Rectangle(578, 250, 220, 215), ImageManager.PLAY_OFFLINE_LEVELS_BUTTON);
		playOfflineBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//임시 비활성화
				//ManagerManager.pm.GoToOfflineMapSelectPanel();
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
		this.add(playOfflineBtn);
		
		otherModeSelectBtn = new TriggeredButton(new Rectangle(855, 270, 176, 174), ImageManager.PLAY_OTHERS_BUTTON);
		otherModeSelectBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToOtherModeSelectPanel();
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
		this.add(otherModeSelectBtn);
		
		
		Functions.playSoundTrack(SoundManager.MAIN_LOOP);
		backgroundTiles.add(new Rectangle(15, 50, 250, 300));
		backgroundTiles.add(new Rectangle(175, 365, 90, 100));
		backgroundTiles.add(new Rectangle(275, 100, 90, 300));
	}
	
	public void finalize() {
		
	}
}