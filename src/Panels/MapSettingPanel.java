package Panels;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Core.Functions;
import Core.Global;
import Managers.FontManager;
import Managers.ImageManager;
import Managers.ManagerManager;
import Objects.TriggeredButton;
import Objects.Map.Map;

public class MapSettingPanel extends JPanel{
	Functions f = new Functions();
	Map curMap = null;
	TriggeredButton goBackBtn, chooseBgmBtn, editMapBtn;
	JFileChooser bgmChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	String bgmFilename = "";
	
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
		g.fillRoundRect(getSize().width/2 - 425, 30, 850, 110, 50, 50);
		f.drawFancyString(g, curMap.getMapName(), getSize().width/2 - f.getFontWidth(g, curMap.getMapName(), 60f)/2, 55, 65f, Color.WHITE);
		
		goBackBtn.draw(g, 20, 15);
		
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_LEFT_LOWER_TILE, 0, 549);
		f.drawImage(g, ImageManager.OTHER_MAPS_CORNER_RIGHT_LOWER_TILE, getSize().width - 195, 549);
		
		
		editMapBtn.draw(g, 480, 300);
		f.drawImage(g, ImageManager.TEST_MY_LEVEL_BUTTON, 760, 300);
		
		chooseBgmBtn.draw(g, 265, 190);
		boolean bfn = bgmFilename.equals("");
		f.drawFancyString(g, bfn?"click to select your bgm":bgmFilename, 340, 195, 30f, bfn?Color.WHITE:Color.ORANGE);
	}
	
	public MapSettingPanel(JFrame frame, Map map) {
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
		
		curMap = map;
		
		editMapBtn = new TriggeredButton(new Rectangle(480, 300, 168, 168), ImageManager.EDIT_MY_LEVEL_BUTTON);
		editMapBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToEditMapPanel(map);
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
		this.add(editMapBtn);
		
		goBackBtn = new TriggeredButton(new Rectangle(20, 15, 71, 80), ImageManager.GO_BACK_GREEN_BUTTON);
		goBackBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.GoToCreatedMapListPanel();
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
		
		chooseBgmBtn = new TriggeredButton(new Rectangle(265, 190, 60, 40), ImageManager.BGM_SELECT_BUTTON);
		chooseBgmBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				FileNameExtensionFilter filter = new FileNameExtensionFilter(
//						"asdfv","dfd"
//						);
//				bgmChooser.setFileFilter(filter);
//				
//				int ret = bgmChooser.showOpenDialog(null);
//				if(ret != JFileChooser.APPROVE_OPTION)return;
//				String name = bgmChooser.getSelectedFile().getName();
//				bgmFilename = bgmChooser.getSelectedFile().getName();
				FileDialog files = new FileDialog(frame, "open", FileDialog.LOAD);
				files.setFile("*.wma");
				files.setDirectory(Paths.get("").toAbsolutePath().toString()+"\\AppData\\BGMs");
				files.setVisible(true);
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
		this.add(chooseBgmBtn);
	}
	
	@Override
	public void finalize() {
		
	}
}
