package Managers;

import java.awt.Font;

public class FontManager {
	public static Font DefaultFont;
	
	public static Font getFont(float size) {
		return DefaultFont.deriveFont(size);
	}
}
