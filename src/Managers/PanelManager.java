package Managers;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import Core.Functions;
import Core.Constants.Global;
import Objects.Map.Map;
import Panels.CreatedMapListPanel;
import Panels.EditMapPanel;
import Panels.LogPanel;
import Panels.MainPanel;
import Panels.MapSettingPanel;
import Panels.OfflineMapSelectPanel;
import Panels.OtherModeSelectPanel;
import Panels.SelectMultiplayModePanel;

public class PanelManager {
	public static JFrame frame = new JFrame("Geometry Dash");
	public static LogPanel logPanel;
	public static MainPanel mainPanel;
	public static OfflineMapSelectPanel offlineMapSelectPanel;
	public static OtherModeSelectPanel otherModeSelectPanel;
	public static MapSettingPanel mapSettingPanel;
	public static CreatedMapListPanel createdMapListPanel;
	public static EditMapPanel editMapPanel;
	public static SelectMultiplayModePanel selectMultiplayModePanel;
	
	public static void InitialExecute() {
		Functions.setInitialFrameBounds(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocation(-3500, 150);
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_F3) {
					Global.isLogViewMode = !Global.isLogViewMode;
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
		
		
		
		mainPanel = new MainPanel(frame);
		logPanel = new LogPanel(frame);
		frame.add(logPanel);
		frame.add(mainPanel);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					frame.repaint();
					try {
						Thread.sleep(1000/144);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	
		GoToSelectMultiplayModePanel();
	}
	
	public static void GoToMainPanel() {
		frame.getContentPane().removeAll();
		mainPanel = new MainPanel(frame);
		
		frame.add(logPanel);
		frame.add(mainPanel);
	}

	public static void GoToOfflineMapSelectPanel() {
		frame.getContentPane().removeAll();
		offlineMapSelectPanel = new OfflineMapSelectPanel(frame);
		
		frame.add(logPanel);
		frame.add(offlineMapSelectPanel);
	}
	
	public static void GoToOtherModeSelectPanel() {
		frame.getContentPane().removeAll();
		otherModeSelectPanel = new OtherModeSelectPanel(frame);
		
		frame.add(logPanel);
		frame.add(otherModeSelectPanel);
	}
	
	public static void GoToMapSettingPanel(Map map) {
		frame.getContentPane().removeAll();
		mapSettingPanel = new MapSettingPanel(frame, map);
		
		frame.add(logPanel);
		frame.add(mapSettingPanel);
	}

	public static void GoToCreatedMapListPanel() {
		frame.getContentPane().removeAll();
		createdMapListPanel = new CreatedMapListPanel(frame);
		
		frame.add(logPanel);
		frame.add(createdMapListPanel);
	}
	
	public static void GoToEditMapPanel(Map map) {
		frame.getContentPane().removeAll();
		editMapPanel = new EditMapPanel(frame, map);
		
		frame.add(logPanel);
		frame.add(editMapPanel);
	}
	
	public static void GoToSelectMultiplayModePanel() {
		frame.getContentPane().removeAll();
		selectMultiplayModePanel = new SelectMultiplayModePanel(frame);
		
		frame.add(logPanel);
		frame.add(selectMultiplayModePanel);
	}
}