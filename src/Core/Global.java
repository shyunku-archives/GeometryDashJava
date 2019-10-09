package Core;

import java.awt.Color;
import java.awt.Point;

import Objects.DoubleCoordinate;

public class Global {
	public static String version = "v0.6.9_beta";
	public static Point mouse = new Point(0,0);
	
	public static boolean isLogViewMode = true;
	
	
	public static long startFlag = 0;
	public static long drawTick = 0;
	
	public static long checkStart = 0;
	public static long checkEnd = 0;
	
	//editor
	public static DoubleCoordinate editorMouseCoordinate = new DoubleCoordinate(0,0);
	public static Point originCoordinate = new Point(0,0);
	
	public static final Color TRANSLUCENT = new Color(0,0,0,0);
}
