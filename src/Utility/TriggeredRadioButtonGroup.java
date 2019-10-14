package Utility;

import java.awt.Color;
import java.awt.Dimension;
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

import Core.Functions;

public abstract class TriggeredRadioButtonGroup extends ButtonGroup{
	private HashMap<Integer, FocusableRadioButton> buttons = new HashMap<>();
	public int curFocused = -1;
	
	public static final int EDITOR_BUILD_BUTTON = 3000;
	public static final int EDITOR_EDIT_BUTTON = 3001;
	public static final int EDITOR_DELETE_BUTTON = 3002;
	//gameobject = type*1000 + id
	
	public void addButtons(JPanel panel, int Flag, int focused, int unfocused, int x, int y) {
		FocusableRadioButton newBtn = new FocusableRadioButton(focused, unfocused, new Point(x,y));
		newBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isTouchable())
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
	
	public void addButtons(JPanel panel, int Flag, int x, int y, Dimension d) {
		FocusableRadioButton newBtn = new FocusableRadioButton(new Point(x,y), d.width, d.height);
		newBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isTouchable())
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
	
	public void printStatus() {
		Functions.print("Button Status --------");
		Set<Integer> keyset = buttons.keySet();
		Iterator iter = keyset.iterator();
		Functions.print("id\t\tstatus");
		while(iter.hasNext()) {
			int cur = (int)iter.next();
			Functions.print(cur+"\t\t"+(buttons.get(cur).getFocus()?"focused":"unfocused"));
		}
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
		if(!this.isTouchable())return;
		Set set = buttons.keySet();
		Iterator<Integer> iter = set.iterator();
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		while(iter.hasNext()) {
			int id = iter.next();
			FocusableRadioButton bt= buttons.get(id);
			buttons.get(id).draw(g);
			g.drawString(""+id, bt.getX(), bt.getY());
		}
		g.setColor(c);
	}
	
	public abstract boolean isTouchable();
}
