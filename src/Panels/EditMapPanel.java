package Panels;

import java.awt.BasicStroke;
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
import Objects.Map.Map;
import Objects.Map.ZoomEngine;

public class EditMapPanel extends JPanel{
	Functions f = new Functions();
	Map curMap = null;
	double[] zoomStage = {2, 1.66, 1.33, 1, 0.9, 0.75, 0.66, 0.5, 0.33};
	ZoomEngine zoomG = new ZoomEngine(zoomStage, 3);
	Point curp = new Point(0, 0);
	TriggeredButton zoomInBtn, zoomOutBtn;
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		Global.editModePos = curp;
		
		Functions.drawImage(g, ImageManager.EDIT_UPPER_BG_TILE, 0, 0);
		Functions.drawImage(g, ImageManager.EDIT_LOWER_BG_TILE, 0, 512);
		
		GradientPaint gp = new GradientPaint(0, 0, new Color(0,98,255,180), 0, getSize().height, new Color(0, 54, 160,204), true);
		Paint original = g.getPaint();
		g.setPaint(gp);
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		g.setPaint(original);
		
		//GRID
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		//y-axis parallel
		for(int i=0;;i++) {
			int cameraX = 200-curp.x+(i+1)*(int)(Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
			if(cameraX<0)continue;
			if(cameraX>1400)break;
			g.drawLine(cameraX, 0, cameraX, 1500);
		}
		//x-axis parallel
		for(int i=0;;i++) {
			int cameraY = 500+curp.y-(i-15)*(int)(Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
			if(cameraY<0)break;
			if(cameraY>800)continue;
			if(curp.x<200)
				g.drawLine(200-curp.x, cameraY, 1500, cameraY);
			else
				g.drawLine(0, cameraY, 1500, cameraY);
		}
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(2));
		g.drawLine(0, 500+curp.y, (int)(getSize().width*1.2), 500+curp.y);
		g.setColor(Color.GREEN);
		g.drawLine(200-curp.x, 0, 200-curp.x, getSize().height);
		
		//EDIT POS
		g.setColor(Color.RED);
		g.fillOval(200-3, 500-3, 6, 6);
		
		//EDIT PANEL
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 600, getSize().width, 300);
		
		zoomInBtn.draw(g);
		zoomOutBtn.draw(g);
		f.drawFancyString(g, "x"+zoomG.getCurZoomRate(), 30, 450, 25f, Color.ORANGE);
	}
	
	public EditMapPanel(JFrame frame, Map map) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		curMap = map;
		//load
		
		zoomInBtn = new TriggeredButton(new Rectangle(30, 300, 60, 60), ImageManager.EDIT_ZOOM_IN_BUTTON);
		zoomInBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				zoomG.zoomIn();
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
		add(zoomInBtn);
		
		zoomOutBtn = new TriggeredButton(new Rectangle(30, 370, 60, 60), ImageManager.EDIT_ZOOM_OUT_BUTTON);
		zoomOutBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				zoomG.zoomOut();
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
		add(zoomOutBtn);
		
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int dx = e.getX() - Global.mouse.x;
				int dy = e.getY() - Global.mouse.y;
				Point p = curp;
				curp = new Point(p.x - dx, p.y + dy);
				if(curp.x < -200)curp.x=-200;
				if(curp.y < -150)curp.y=-150;
				Global.mouse = new Point(e.getX(), e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				Global.mouse = new Point(e.getX(), e.getY());
			}
			
		});
	}
	
	@Override
	public void finalize() {
		
	}
}
