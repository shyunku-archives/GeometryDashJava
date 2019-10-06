package Objects;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class TriggeredRadioButtonGroup extends ButtonGroup{
	public TriggeredRadioButtonGroup() {
		
	}
	
	private class CustomRadioButton{
		public BufferedImage image;
		public Point p;
		public CustomRadioButton(BufferedImage bi, Point p) {
			this.image = bi;
			this.p = p;
		}
	}
}
