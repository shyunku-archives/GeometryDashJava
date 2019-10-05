package Core.Constants;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Dimensions {
	public static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1366, 768);
	public static final Dimension[] SMALL_WINDOW_SIZE = {
			new Dimension(1280, 720),
			new Dimension(1366, 768),
			new Dimension(1600, 900),
			new Dimension(1920, 1080),
			new Dimension(2560, 1440)
	};
	
	
	//functions
	public static Dimension getScreenResolution() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
	}
}
