package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Managers.ImageManager;
import Map.Core.Map;
import Map.Engines.Layer;
import Map.Engines.PlayerSkinImage;

public class LocalGamePanel extends JPanel{
	private Map curMap;
	private PlayerSkinImage skinEngine;
	private GameEngine gameEngine;
	
	Functions f = new Functions();
	
	private float tileMovementSpeed = 0.072f;
	
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
		
		
		//f.drawFancyString(g, new String("Game Start"), 530, 500, 45f, false);
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,0,0,0), getSize().width/2, 0, Color.WHITE, true);
		g.setPaint(gp);
		g.setStroke(new BasicStroke(3));
		g.drawLine(0, 530, getSize().width, 530);
	}
	
	public LocalGamePanel(JFrame frame, Map map) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		curMap = map;
		gameEngine = new GameEngine(curMap.getEnvironment());
	}
}
