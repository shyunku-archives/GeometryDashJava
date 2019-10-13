package Utility;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TriggeredRadioButtonGroup extends ButtonGroup{
	private HashMap<Integer, FocusableRadioButton> buttons = new HashMap<>();
	public int curFocused = -1;
	
	public static final int EDITOR_BUILD_BUTTON = 3000;
	public static final int EDITOR_EDIT_BUTTON = 3001;
	public static final int EDITOR_DELETE_BUTTON = 3002;
	
	
	public void addButtons(JPanel panel, int Flag, int focused, int unfocused, int x, int y) {
		FocusableRadioButton newBtn = new FocusableRadioButton(focused, unfocused, new Point(x,y));
		newBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				focus(Flag);
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
		
		buttons.put(Flag, newBtn);
		panel.add(newBtn);
	}
	
	public void focus(int Flag) {
		if(curFocused!=-1)
			buttons.get(curFocused).unfocusThis();
		curFocused = Flag;
		buttons.get(Flag).focusThis();
	}
	
	public void setListener(int Flag, TriggeredButtonListener listener) {
		buttons.get(Flag).setListener(listener);
	}
	
	public void drawAll(Graphics2D g) {
		Set set = buttons.keySet();
		Iterator<Integer> iter = set.iterator();
		while(iter.hasNext()) {
			int id = iter.next();
			buttons.get(id).draw(g);
		}
	}
}
