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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Functions;
import Core.Global;
import Core.Constants.Area;
import Managers.ImageManager;
import Managers.ManagerManager;
import Map.Core.Map;
import Utility.TriggeredButton;
import Utility.TriggeredButtonListener;
import Utility.TriggeredTextArea;
import Utility.VirtualScroller;

public class CreatedMapListPanel extends JPanel{
	Functions f = new Functions();
	TriggeredTextArea newLevelNameTextArea;
	TriggeredButton goBackBtn, createMapBtn;
	ArrayList<Map> maps = new ArrayList<>();
	ArrayList<TriggeredButton> viewButton = new ArrayList<>();
	VirtualScroller vscroll = new VirtualScroller();
	String WarningStr = "";
	
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
		
		g.setColor(new Color(0,55, 163));
		g.fillRoundRect(getSize().width/2 - 300, 30, 600, 70, 50, 50);
		
		f.drawFancyString(g, "create", 1080, 35, 30f, Color.WHITE);
		f.drawFancyString(g, "new level", 1080, 65, 30f, Color.WHITE);
		
		String areatext = newLevelNameTextArea.getText();
		f.drawFancyString(g, areatext, getSize().width/2 - f.getFontWidth(g, areatext, 45f)/2, 43, 45f, Color.WHITE);
		
		g.setColor(new Color(191, 114, 62));
		g.fillRect(310, 190, 740, 435);
		
		
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_RIGHT_LOWER_TILE, getSize().width - 195, 549);
		createMapBtn.draw(g, 1000, 35);
		
		f.drawImage(g, f.resizeImage(ImageManager.LEFT_SIDE_BAR, 30, 430), 298, 195);
		f.drawImage(g, f.resizeImage(ImageManager.RIGHT_SIDE_BAR, 30, 430), 1027, 195);
		
		f.drawImage(g, ImageManager.MY_LEVELS_UPPER_BAR, 285, 130);
		f.drawImage(g, ImageManager.MY_LEVELS_LOWER_BAR, 290, 620);
		
		goBackBtn.draw(g, 20, 15);
		
		f.drawFancyString(g, WarningStr, getSize().width/2 - f.getFontWidth(g, WarningStr, 20f)/2, 105, 20f, 
				WarningStr.equals("added!")?Color.GREEN:Color.RED);
		
		g.setColor(Color.BLACK);
		Rectangle rect = Area.MY_LEVELS_SCROLL_AREA;
		g.setClip(rect);
		int scroll = vscroll.getCoordinate(20);
		for(int i=0;i<maps.size();i++) {
			int height = 100;
			g.drawLine(rect.x, rect.y+(i+1)*height - scroll, rect.x+rect.width, rect.y+(i+1)*height - scroll);
			f.drawFancyString(g, maps.get(i).getHeader().getMapName(), rect.x + 25, rect.y + 10 +i*height - scroll, 45f, Color.WHITE);
			f.drawImage(g, ImageManager.VIEW_BUTTON, rect.x + 550, rect.y + 25 +i*height - scroll);
			f.drawFancyString(g, "view", rect.x + 565, rect.y + 30 +i*height - scroll, 40f, Color.WHITE);
			
			viewButton.get(i).setBounds(rect.x + 550, rect.y + 25 +i*height - scroll, 121, 54);
		}
	}
	
	public CreatedMapListPanel(JFrame frame) {
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
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 71, 80), ImageManager.GO_BACK_GREEN_BUTTON);
		goBackBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToOtherModeSelectPanel();
			}
			
		});
		this.add(goBackBtn);
		
		createMapBtn = new TriggeredButton(new Rectangle(1000, 35, 60, 60), ImageManager.CREATE_NEW_LEVEL_BUTTON);
		createMapBtn.setClickListener(new TriggeredButtonListener() {
			@Override
			public void onClickListener() {
				String name = newLevelNameTextArea.getText();
				if(name.length()==0) {
					WarningStr = "please enter map name";
					return;
				}
				for(int i=0;i<maps.size();i++)
					if(maps.get(i).getHeader().getMapName().equals(name)) {
						WarningStr = "set another name (there is same file name)";
						return;
					}
				
				maps.add(new Map(name));
				ManagerManager.pm.GoToMapSettingPanel(new Map(name));
			}
			
		});
		this.add(createMapBtn);
		
		newLevelNameTextArea = new TriggeredTextArea(new Rectangle(383, 30, 600, 90), 15, false);
		this.add(newLevelNameTextArea);
		
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				vscroll.scroll(e.getWheelRotation());
			}
			
		});
		
		File folder = new File("AppData\\Maps\\Local");
		int indexA = 0;
		for(final File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {
				final int indexB = indexA;
				Map newone = new Map(fileEntry);
				newone.printJson();
				maps.add(newone);
				TriggeredButton newBtn = new TriggeredButton(new Rectangle(0,0,0,0), ImageManager.NULL);
				newBtn.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if(Area.MY_LEVELS_SCROLL_AREA.contains(Global.mouse))
							ManagerManager.pm.GoToMapSettingPanel(maps.get(indexB));
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
				viewButton.add(newBtn);
				indexA++;
			}
		}
		
		for(int i=0;i<viewButton.size();i++)
			this.add(viewButton.get(i));
		
		vscroll.setThis(0, maps.size()*5 - 21);
	}
	
	@Override
	public void finalize() {
		
	}
}
