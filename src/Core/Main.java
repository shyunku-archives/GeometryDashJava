package Core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import Core.Constants.Dimensions;
import Core.Constants.Global;
import Core.Constants.ManagerManager;
import Managers.ErrorManager;
import Managers.FontManager;
import Managers.ImageManager;
import Managers.PanelManager;
import Managers.SoundManager;
import Network.Engine.NetworkManager;

public class Main {
	public static void main(String[] args) {
		ManagerManager.sm = new SoundManager();
		ManagerManager.im = new ImageManager();
		ManagerManager.pm = new PanelManager();
		ManagerManager.em = new ErrorManager();
		ManagerManager.nm = new NetworkManager();
		
		Global.startFlag = System.currentTimeMillis();
		
		try {
			FontManager.DefaultFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\font\\PUSAB.otf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ManagerManager.pm.InitialExecute();
	}
}
