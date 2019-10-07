package Utility;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class TriggeredRadioButtonGroup extends ButtonGroup{
	private HashMap<Integer, FocusableRadioButton> buttons = new HashMap<>();
	int curFocused = -1;
	
	public static final int EDITOR_BUILD_BUTTON = 3000;
	public static final int EDITOR_EDIT_BUTTON = 3001;
	public static final int EDITOR_DELETE_BUTTON = 3002;
	
	
	public void addButtons(int Flag, int focused, int unfocused, int x, int y) {
		buttons.put(Flag, new FocusableRadioButton(focused, unfocused, new Point(x,y)));
	}
	
	public void focus(int Flag) {
		if(curFocused!=-1)
			buttons.get(curFocused).unfocusThis();
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
