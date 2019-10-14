package Panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Managers.ImageManager;
import Managers.ManagerManager;
import Map.Core.Map;
import Map.Core.MapObjectImage;
import Map.Engines.GameObjectManager;
import Map.Engines.ZoomEngine;
import Map.Objects.Block;
import Map.Objects.Decoration;
import Map.Objects.JumpBall;
import Map.Objects.Spike;
import Objects.DoubleCoordinate;
import Utility.FocusableRadioButton;
import Utility.HierarchySelection;
import Utility.Pair;
import Utility.TriggeredButton;
import Utility.TriggeredRadioButtonGroup;

public class EditMapPanel extends JPanel{
	Functions f = new Functions();
	Map curMap = null;
	double[] zoomStage = {2, 1.66, 1.33, 1, 0.9, 0.75, 0.66, 0.5, 0.33};
	ZoomEngine zoomG = new ZoomEngine(zoomStage, 3);
	TriggeredButton zoomInBtn, zoomOutBtn, saveMapBtn, goBackBtn;
	Point origin = new Point(200,500);			//원점의 화면상 실제 좌표
	
	TriggeredRadioButtonGroup modeGroup;
	TriggeredRadioButtonGroup objectCategory;
	HashMap<Integer, TriggeredRadioButtonGroup> objectGroup = new HashMap<>();
	GameObjectManager objectManager = new GameObjectManager();
	Set<Integer> objectGroupKeySet;
	
	
	
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
			int axisCoord = origin.x+(int)((i+1)*Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
			if(axisCoord<0)continue;
			if(axisCoord>1400)break;
			g.drawLine(axisCoord, 0, axisCoord, 1500);
		}
		//x-axis parallel
		for(int i=0;;i++) {
			int axisCoord = origin.y-(int)((i-15)*Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate());
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
		
		
		Iterator<Integer> iter = objectGroupKeySet.iterator();
		while(iter.hasNext()) {
			int id = iter.next();
			objectGroup.get(id).drawAll(g);
		}
		
		//EDIT PANEL
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 600, getSize().width, 300);
		
		g.setColor(Color.WHITE);
		zoomInBtn.draw(g);
		zoomOutBtn.draw(g);
		f.drawFancyString(g, "x"+zoomG.getCurZoomRate(), 30, 450, 25f, Color.ORANGE);
		
		
		
		if(modeGroup.curFocused == TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON) {
			//빌드 모드 - focus 설정 필요
			int alltype = objectManager.getNum(MapObjectImage.TYPE_ALL);
			for(int i=0;i<alltype;i++) {
				Pair<Integer, BufferedImage> pair = objectManager.getRepresentType(i);
				int type = pair.first;
				BufferedImage bi = pair.second;
				int x = i/2;
				int y = i%2;
				f.drawImage(g, ImageManager.EDITOR_OBJECT_SELCET_BUTTON, 150+60*x, 610+60*y);
				f.drawImage(g, bi, 175+60*x - bi.getWidth()/2, 650+60*y - bi.getHeight());
//				f.drawFancyString(g, MapObjectImage.typeIntegerToString(type), 
//						175+60*x - bi.getWidth()/2, 650+60*y - bi.getHeight(), 10f, Color.WHITE);
			}
			int curtype = objectCategory.curFocused/10000;
			int num = objectManager.getNum(curtype);
			for(int i=0;i<num;i++) {
				int x = i/2;
				int y = i%2;
				BufferedImage bi = f.getGameObject(curtype, i, 30, 30);
				f.drawImage(g, ImageManager.EDITOR_OBJECT_SELCET_BUTTON, 460+60*x, 610+60*y);
				f.drawImage(g, bi, 485+60*x - bi.getWidth()/2, 650+60*y - bi.getHeight());
			}
		}
		
		//radio buttons
		modeGroup.drawAll(g);
		objectCategory.drawAll(g);
		
		saveMapBtn.draw(g);
		goBackBtn.draw(g);
		//map data
		curMap.getMapData().drawAll(g, zoomG.getCurZoomRate());
		
		gp = new GradientPaint(0, 664, Color.WHITE, 0, getSize().height - 50, new Color(0,0,0,0), true);
		g.setPaint(gp);
		g.setStroke(new BasicStroke(2));
		g.drawLine(140, 610, 140, 718);
		g.drawLine(450, 610, 450, 718);
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
		dx/=(double)Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate();
		dy/=(double)Map.DEFAULT_GRID_SIZE*zoomG.getCurZoomRate();
		return new DoubleCoordinate(dx, dy);
	}
	
	public EditMapPanel(JFrame frame, Map map) {
		this.setSize(frame.getSize());
		this.setVisible(true);
		this.setLayout(null);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//non-editor-panel 영역이어야 하고, build 모드여야함, x좌표도 0이상이어야함
				if(e.getY()<600) {
					if(modeGroup.curFocused == TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON) {
						DoubleCoordinate editPos = Global.editorMouseCoordinate;
						if(editPos.x>0) {
							double fixedX = (double)((int)editPos.x);
							double fixedY = (double)((int)editPos.y);
							DoubleCoordinate fixedPos = new DoubleCoordinate(fixedX, fixedY);
							int type = objectCategory.curFocused/10000;
							int id = objectGroup.get(type).curFocused%1000;
							
							switch(type) {
							case MapObjectImage.TYPE_BLOCK:
								curMap.getMapData().addBlock(new Block(id, fixedPos));
								break;
							case MapObjectImage.TYPE_SPIKE:
								curMap.getMapData().addSpike(new Spike(id, fixedPos));
								break;
							case MapObjectImage.TYPE_JUMPBALL:
								curMap.getMapData().addJumpBall(new JumpBall(id, fixedPos));
								break;
							case MapObjectImage.TYPE_DECORATION:
								curMap.getMapData().addDecoration(new Decoration(id, fixedPos));
								break;
							}
						}
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		curMap = map;
		//load
		
		modeGroup = new TriggeredRadioButtonGroup() {
			@Override
			public boolean isTouchable() {
				return true;
			}
		};
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON,
				ImageManager.FOCUSED_BUILD_BUTTON, ImageManager.UNFOCUSED_BUILD_BUTTON, 15, 610);
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_EDIT_BUTTON,
				ImageManager.FOCUSED_EDIT_BUTTON, ImageManager.UNFOCUSED_EDIT_BUTTON, 15, 648);
		modeGroup.addButtons(this, TriggeredRadioButtonGroup.EDITOR_DELETE_BUTTON,
				ImageManager.FOCUSED_DELETE_BUTTON, ImageManager.UNFOCUSED_DELETE_BUTTON, 15, 686);
		
		modeGroup.focus(TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON);
		
		objectCategory = new TriggeredRadioButtonGroup() {
			@Override
			public boolean isTouchable() {
				return modeGroup.curFocused == TriggeredRadioButtonGroup.EDITOR_BUILD_BUTTON;
			}
			
		};
		int alltype = objectManager.getNum(MapObjectImage.TYPE_ALL);
		for(int i=0;i<alltype;i++) {
			Pair<Integer, BufferedImage> pair = objectManager.getRepresentType(i);
			final int type = pair.first;
			int categoryIndex = type*10000;
			int thistype = objectManager.getNum(type);
			BufferedImage bi = pair.second;
			int x = i/2;
			int y = i%2;
			TriggeredRadioButtonGroup objects = new TriggeredRadioButtonGroup() {
				@Override
				public boolean isTouchable() {
					// TODO Auto-generated method stub
					int typen = objectCategory.curFocused/10000;
					return objectCategory.isTouchable()&&(type == typen);
				}
			};
			objectCategory.addButtons(this, type*10000, 150+60*x, 610+60*y, new Dimension(50,50));
			for(int j=0;j<thistype;j++) {
				int q = j/2;
				int t = j%2;
				objects.addButtons(this, type*1000+j, 460+60*q, 610+60*t, new Dimension(50,50));
			}
			if(thistype>0)
				objects.focus(type*1000);
			objectGroup.put(type, objects);
		}
		objectCategory.focus(MapObjectImage.TYPE_BLOCK*10000);
		objectGroupKeySet = objectGroup.keySet();
		
		
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
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 71, 80), ImageManager.GO_BACK_GREEN_BUTTON);
		goBackBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToMapSettingPanel(curMap);
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
		
		saveMapBtn = new TriggeredButton(new Rectangle(1300, 610, 60, 60), ImageManager.EDITOR_SAVE_MAP_BUTTON);
		saveMapBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				curMap.saveThis();
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
		add(saveMapBtn);
		
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
				if(Global.mouse.y>600)return;
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
