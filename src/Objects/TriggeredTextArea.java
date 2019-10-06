package Objects;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

import javax.swing.InputMap;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

import Core.Constants.ManagerManager;
import Engines.DocumentSizeFilter;
import Managers.FontManager;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TriggeredTextArea extends JTextArea{
	public TriggeredTextArea(Rectangle rect, int limit) {
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(limit));
		this.setDocument(doc);
		setWrapStyleWord(true);
		setLineWrap(true);
		setBounds(rect);
		setText("");
		setCaretColor(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		setForeground(new Color(0,0,0,0));
		this.setFont(FontManager.getFont(0f));
		DefaultCaret caret = (DefaultCaret)this.getCaret();
		disableKeys(getInputMap(), new String[]{"ENTER", "LEFT", "RIGHT", "UP", "DOWN"});
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ManagerManager.pm.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
	}
	
	private static void disableKeys(InputMap im,String[] keystrokeNames) {              
        for (int i = 0; i < keystrokeNames.length; ++i)
            im.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
    }
}