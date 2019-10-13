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
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;
import Map.Core.Map;
import Map.Engines.ZoomEngine;
import Objects.DoubleCoordinate;
import Utility.TriggeredButton;
import Utility.TriggeredRadioButtonGroup;

public class EditMapPanel extends JPanel{
	Functions f = new Functions();
	Map curMap = null;
	double[] zoomStage = {2, 1.66, 1.33, 1, 0.9, 0.75, 0.66, 0.5, 0.33};
	ZoomEngine zoomG = new ZoomEngine(zoomStage, 3);
	TriggeredButton zoomInBtn, zoomOutBtn;
	Point origin = new Point(200,500);			//원점의 화면상 실제 좌표
	
	TriggeredRadioButtonGroup modeGroup = new TriggeredRadioButtonGroup();
	
	
	@Override
	public void paintComponent(Graphics gd) {
		super.paintComponent(gd);
		final Graphics2D g = (Graphics2D) gd;
		Functions.smoothRendering(g);
		Global.drawTick++;
		
		Global.editorMouseCoordinate = getEditorCoordinate(Global.mouse);
		Global.originCoordinate = origin;
		
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
			int axisCoord = origin.x+(i+1)*(int)(Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
			if(axisCoord<0)continue;
			if(axisCoord>1400)break;
			g.drawLine(axisCoord, 0, axisCoord, 1500);
		}
		//x-axis parallel
		for(int i=0;;i++) {
			int axisCoord = origin.y-(i-15)*(int)(Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
			if(axisCoord<0)break;		//화면 위로 넘어가면 break
			if(axisCoord>800)continue;	//화면 아래 것들은 continue
			if(origin.x>0)
				g.drawLine(origin.x, axisCoord, 1500, axisCoord);
			else
				g.drawLine(0, axisCoord, 1500, axisCoord);
		}
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(2));
		g.drawLine(0, origin.y, (int)(getSize().width*1.2), origin.y);
		g.setColor(Color.GREEN);
		g.drawLine(origin.x, 0, origin.x, getSize().height);
		
//		g.setColor(Color.PINK);
//		Point cent = new Point(getSize().width/2, 300);
//		g.fillOval(cent.x-3, cent.y-3, 6,6);
//		g.drawString(getEditorCoordinate(cent).getPosByString(), cent.x, cent.y-7);
		
		//EDIT PANEL
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 600, getSize().width, 300);
		
		g.setColor(Color.WHITE);
		
		
		zoomInBtn.draw(g);
		zoomOutBtn.draw(g);
		f.drawFancyString(g, "x"+zoomG.getCurZoomRate(), 30, 450, 25f, Color.ORANGE);
		
		gp = new GradientPaint(0, 664, Color.WHITE, 0, getSize().height - 50, new Color(0,0,0,0), true);
		g.setPaint(gp);
		g.setStroke(new BasicStroke(2));
		g.drawLine(140, 610, 140, 718);
		
		
		modeGroup.drawAll(g);
	}
	
	private void forceCameraMoveWhenZoom(double rate) {
		int dx = getSize().width/2 - origin.x;
		int dy = origin.y - 300;
		
		double newr = rate-1;
		
		origin.x -= newr*dx;
		origin.y += newr*dy;
	}
	
	public DoubleCoordinate getEditorCoordinate(Point real) {
		double dx = real.x - origin.x;
		double dy = -(real.y - origin.y);
		dx/=Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate();
		dy/=Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate();
		return new DoubleCoordinate(dx, dy);
	}
	
	public EditMapPanel(JFrame frame, Map map) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		curMap = map;
		//load
		
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON,
				ImageManager.FOCUSED_BUILD_BUTTON, ImageManager.UNFOCUSED_BUILD_BUTTON, 15, 610);
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_EDIT_BUTTON,
				ImageManager.FOCUSED_EDIT_BUTTON, ImageManager.UNFOCUSED_EDIT_BUTTON, 15, 648);
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_DELETE_BUTTON,
				ImageManager.FOCUSED_DELETE_BUTTON, ImageManager.UNFOCUSED_DELETE_BUTTON, 15, 686);
		
		modeGroup.focus(TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON);
		
		zoomInBtn = new TriggeredButton(new Rectangle(30, 300, 60, 60), ImageManager.EDIT_ZOOM_IN_BUTTON);
		zoomInBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				forceCameraMoveWhenZoom(zoomG.zoomIn());
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
				forceCameraMoveWhenZoom(zoomG.zoomOut());
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
				origin = new Point(origin.x + dx, origin.y + dy);
				
				if(origin.x>200)origin.x=200;
				if(origin.y<500)origin.y=500;
				
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
