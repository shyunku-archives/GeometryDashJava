package Objects;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.InputMap;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

import Engines.DocumentSizeFilter;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.Toolkit;

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
		disableKeys(getInputMap(), new String[]{"ENTER"});
	}
	
	private static void disableKeys(InputMap im,String[] keystrokeNames) {              
        for (int i = 0; i < keystrokeNames.length; ++i)
            im.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
        
    }
}