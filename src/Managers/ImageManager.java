package Managers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

import Core.Functions;
import Exceptions.FatalException;
import Map.Core.MapObjectImage;
import Utility.Pair;

public class ImageManager {
	public static final int NULL = -9999;
	
	public static final int MAIN_LOGO = 2000;
	public static final int PLAY_OFFLINE_LEVELS_BUTTON = 2001;
	public static final int PLAY_OTHERS_BUTTON = 2002;
	public static final int SKIN_SETTING_BUTTON = 2003;
	public static final int GAME_BG_TILE = 2004;
	public static final int GAME_GROUND_TILE = 2005;
	public static final int PLAY_OFFLINE_LEVELS_BUTTON_SHADOW = 2006;
	public static final int PLAY_OTHERS_LEVELS_BUTTON_SHADOW = 2007;
	public static final int SKIN_SETTING_BUTTON_SHADOW = 2008;
	public static final int OTHER_MAPS_CORNER_LEFT_LOWER_TILE = 2009;
	public static final int OTHER_MAPS_CORNER_LEFT_UPPER_TILE = 2010;
	public static final int OTHER_MAPS_CORNER_RIGHT_LOWER_TILE = 2011;
	public static final int OTHER_MAPS_CORNER_RIGHT_UPPER_TILE = 2012;
	public static final int CREATE_MAP_BUTTON = 2013;
	public static final int CREATE_NEW_LEVEL_BUTTON = 2014;
	public static final int GO_BACK_GREEN_BUTTON = 2015;
	public static final int MY_LEVELS_UPPER_BAR = 2016;
	public static final int MY_LEVELS_LOWER_BAR = 2017;
	public static final int LEFT_SIDE_BAR = 2018;
	public static final int RIGHT_SIDE_BAR = 2019;
	public static final int GO_BACK_PINK_BUTTON = 2020;
	public static final int VIEW_BUTTON = 2021;
	public static final int EDIT_MY_LEVEL_BUTTON = 2022;
	public static final int TEST_MY_LEVEL_BUTTON = 2023;
	public static final int BGM_SELECT_BUTTON = 2024;
	public static final int EDIT_UPPER_BG_TILE = 2025;
	public static final int EDIT_LOWER_BG_TILE = 2026;
	public static final int EDIT_ZOOM_IN_BUTTON = 2027;
	public static final int EDIT_ZOOM_OUT_BUTTON = 2028;
	public static final int PARTICIPATE_MULTIPLAY_BUTTON = 2029;
	public static final int SERVER_PARTICIPATE_BUTTON = 2030;
	public static final int SERVER_CREATE_BUTTON = 2031;
	public static final int WAITING_ROOM_PLAYER_LIST_UPPER_BAR = 2032;
	public static final int WAITING_ROOM_PLAYER_LIST_LOWER_BAR = 2033;
	public static final int WAITING_ROOM_CHAT_UPPER_BAR = 2034;
	public static final int WAITING_ROOM_CHAT_LOWER_BAR = 2035;
	public static final int WAITING_ROOM_CHAT_MIDDLE_BAR = 2036;
	public static final int FOCUSED_BUILD_BUTTON = 2037;
	public static final int UNFOCUSED_BUILD_BUTTON = 2038;
	public static final int FOCUSED_EDIT_BUTTON = 2039;
	public static final int UNFOCUSED_EDIT_BUTTON = 2040;
	public static final int FOCUSED_DELETE_BUTTON = 2041;
	public static final int UNFOCUSED_DELETE_BUTTON = 2042;
	public static final int EDITOR_OBJECT_SELCET_BUTTON = 2043;
	public static final int BLANK = 2044;
	public static final int EDITOR_SAVE_MAP_BUTTON = 2045;
	
	public static final int PLAYER_SKIN_NORMAL_TYPE = 10000;
	public static final int PLAYER_SKIN_FLIGHT_TYPE = 10001;
	public static final int PLAYER_SKIN_FLIP_TYPE = 10002;
	
	
	public HashMap<Integer, BufferedImage> imageBundle = new HashMap<>();
	public HashMap<Integer, HashMap<Integer, BufferedImage>> gameObjectBundle = new HashMap<>();
	//type, id, bi
	public HashMap<String, HashMap<Integer, BufferedImage>> playerSkinBundle = new HashMap<>();
	
	public ImageManager() {
		putImage(MAIN_LOGO, "logo.png");
		putImage(PLAY_OFFLINE_LEVELS_BUTTON, "play_offline_levels_button.png", 210, 209);
		putImage(PLAY_OTHERS_BUTTON, "play_others_button.png", 170, 170);
		putImage(SKIN_SETTING_BUTTON, "skin_setting_button.png", 170, 170);
		putImage(GAME_BG_TILE, "game_bg_tile.png", 1536, 576);
		putImage(GAME_GROUND_TILE, "ground_tile.png", 256, 256);
		putImage(PLAY_OFFLINE_LEVELS_BUTTON_SHADOW, "main_button_shadow.png", 210, 210, 100);
		putImage(PLAY_OTHERS_LEVELS_BUTTON_SHADOW, "main_button_shadow.png", 170, 170, 100);
		putImage(SKIN_SETTING_BUTTON_SHADOW, "main_button_shadow.png", 170, 170, 100);
		putImage(OTHER_MAPS_CORNER_LEFT_LOWER_TILE, "other_maps_corner_tile.png", 180, 180);
		putImage(OTHER_MAPS_CORNER_LEFT_UPPER_TILE, "other_maps_corner_tile.png", 180, 180, 255, 90);
		putImage(OTHER_MAPS_CORNER_RIGHT_LOWER_TILE, "other_maps_corner_tile.png", 180, 180, 255, 270);
		putImage(CREATE_MAP_BUTTON, "create_map_button.png", 160, 160);
		putImage(CREATE_NEW_LEVEL_BUTTON, "add_button.png", 60, 60);
		putImage(GO_BACK_GREEN_BUTTON, "go_back_green_button.png", 80, 80);
		putResizedImage(MY_LEVELS_UPPER_BAR, "upper_bar.png", 0.5f);
		putResizedImage(MY_LEVELS_LOWER_BAR, "lower_bar.png", 0.5f);
		putImage(LEFT_SIDE_BAR, "side_bar.png");
		putImage(RIGHT_SIDE_BAR, "side_bar.png", 180);
		putImage(GO_BACK_PINK_BUTTON, "go_back_pink_button.png", 71, 80);
		putResizedImage(VIEW_BUTTON, "green_template_button.png", 0.35f);
		putResizedImage(EDIT_MY_LEVEL_BUTTON, "edit_button.png", 0.6f);
		putResizedImage(TEST_MY_LEVEL_BUTTON, "play_button.png", 0.6f);
		putResizedImage(BGM_SELECT_BUTTON, "folder_image.png", 0.4f);
		putImage(EDIT_UPPER_BG_TILE, "game_bg_tile.png", 1366, 512);
		putFlippedImage(EDIT_LOWER_BG_TILE, "game_bg_tile.png", 1366, 512);
		putImage(EDIT_ZOOM_IN_BUTTON, "zoom_in_icon.png", 60, 60);
		putImage(EDIT_ZOOM_OUT_BUTTON, "zoom_out_icon.png", 60, 60);
		putImage(PARTICIPATE_MULTIPLAY_BUTTON, "multiplay_button.png", 160, 160);
		putImage(SERVER_CREATE_BUTTON, "add_button.png", 80, 80);
		putImage(SERVER_PARTICIPATE_BUTTON, "participate_button.png", 80, 80);
		putResizedImage(WAITING_ROOM_PLAYER_LIST_UPPER_BAR, "upper_bar.png", 0.3f);
		putResizedImage(WAITING_ROOM_PLAYER_LIST_LOWER_BAR, "lower_bar.png", 0.3f);
		putResizedImage(WAITING_ROOM_CHAT_UPPER_BAR, "upper_bar.png", 0.3f);
		putResizedImage(WAITING_ROOM_CHAT_LOWER_BAR, "lower_bar.png", 0.3f);
		putImage(WAITING_ROOM_CHAT_MIDDLE_BAR, "side_bar.png", 90);
		putResizedImage(FOCUSED_BUILD_BUTTON, "focused_build_button.png", 0.4f);
		putResizedImage(UNFOCUSED_BUILD_BUTTON, "unfocused_build_button.png", 0.4f);
		putResizedImage(FOCUSED_EDIT_BUTTON, "focused_edit_button.png", 0.4f);
		putResizedImage(UNFOCUSED_EDIT_BUTTON, "unfocused_edit_button.png", 0.4f);
		putResizedImage(FOCUSED_DELETE_BUTTON, "focused_delete_button.png", 0.4f);
		putResizedImage(UNFOCUSED_DELETE_BUTTON, "unfocused_delete_button.png", 0.4f);
		putImage(EDITOR_OBJECT_SELCET_BUTTON, "object_template_button.png", 50, 50);
		putImage(BLANK, "blank.png");
		putImage(EDITOR_SAVE_MAP_BUTTON, "save_button.png", 60, 50);
		
		this.setAllGameObjectImage();
		this.setAllPlayerSkinImage();
	}
	
	private void setAllGameObjectImage() {
		String path = "resources\\Image\\Game";
		File folder = new File(path);
		for(final File entry : folder.listFiles()) {
			if(!entry.isDirectory())continue;
			String foldername = entry.getName();
			int tag = MapObjectImage.typeStringToTag(foldername);
			for(final File inter : entry.listFiles()) {
				String fileIndex = Functions.getFilenameNoExtension(inter.getName());
				int id = Integer.parseInt(fileIndex);
				putGameObjectImage(tag, id);
			}
		}
	}
	
	private void setAllPlayerSkinImage() {
		String path = "resources\\Image\\PlayerSkin";
		File folder = new File(path);
		for(final File entry : folder.listFiles()) {
			if(!entry.isDirectory())continue;
			String foldername = entry.getName();
			for(final File inter : entry.listFiles()) {
				String fileIndex = Functions.getFilenameNoExtension(inter.getName());
				int id = Integer.parseInt(fileIndex);
				try {
					BufferedImage bi = ImageIO.read(inter);
					if(playerSkinBundle.get(foldername)==null) {
						HashMap<Integer, BufferedImage> bundle = new HashMap<>();
						bundle.put(id, bi);
						playerSkinBundle.put(foldername, bundle);
					}else {
						playerSkinBundle.get(foldername).put(id, bi);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public BufferedImage getGameObjectImage(int type, int id, float ratio) {
		BufferedImage img = gameObjectBundle.get(type).get(id);
		if(ratio == 1) return img;
		return Functions.resizeImage(img, (int)(img.getWidth()*ratio), (int)(img.getHeight()*ratio));
	}
	
	public BufferedImage getGameObjectImage(int type, int id, int x, int y) {
		BufferedImage img = null;
		try {
			img = gameObjectBundle.get(type).get(id);
		}catch(NullPointerException e) {
			img = Functions.getImage(this.BLANK);
		}
		return Functions.resizeImage(img, x, y);
	}
	
	private void putGameObjectImage(int type, int id) {
		try {
			BufferedImage bi = ImageIO.read(new File(getGameObjectImagePath(type, id)));
			if(gameObjectBundle.get(type)== null) {
				HashMap<Integer, BufferedImage> bundle = new HashMap<>();
				bundle.put(id, bi);
				gameObjectBundle.put(type, bundle);
			}else
				gameObjectBundle.get(type).put(id, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getPlayerSkinImagePath(int id) {
		return "resources\\Image\\PlayerSkin\\"+id+".png";
	}
	
	private static String getGameObjectImagePath(int type, int id) {
		String typePath = MapObjectImage.typeIntegerToString(type);
		return "resources\\Image\\Game\\"+typePath+"\\"+id+".png";
	}
	
	public static String getImagePath(String filename) {
		return "resources\\Image\\Default\\"+filename;
	}
	
	private void putImage(int key, String name, int w, int h) {
		boolean isfix = w != 0;
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			if(isfix)
				bi = Functions.resizeImage(bi, w, h);
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putImage(int key, String name, double angle) {
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			bi = Functions.rotateBufferdImage(bi, angle);
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putFlippedImage(int key, String name, int w, int h) {
		boolean isfix = w != 0;
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			if(isfix)
				bi = Functions.resizeImage(bi, w, h);
			bi = createFlipped(bi);
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putImage(int key, String name, int w, int h, int opacity, double angle) {
		boolean isfix = w != 0;
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			if(isfix)
				bi = Functions.resizeImage(bi, w, h);
			bi = RenderImageAsOpacity(bi, opacity);
			bi = Functions.rotateBufferdImage(bi, angle);
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putImage(int key, String name, int w, int h, int opacity) {
		boolean isfix = w != 0;
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			if(isfix)
				bi = Functions.resizeImage(bi, w, h);
			bi = RenderImageAsOpacity(bi, opacity);
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putResizedImage(int key, String name, float ratio) {
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			bi = Functions.resizeImage(bi, (int)(bi.getWidth()*ratio), (int)(bi.getHeight()*ratio));
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void putImage(int key, String name) {
		try {
			BufferedImage bi = ImageIO.read(new File(getImagePath(name)));
			imageBundle.put(key, bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage RenderImageAsOpacity(BufferedImage bi, int floats) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				byte alpha = (byte)(floats);
				int alphaBit = ( alpha << 24 ) | 0x00ffffff;
				int colorRGBA = bi.getRGB(j, i) & alphaBit;
				bi.setRGB(j, i, colorRGBA);
			}
		}
		return bi;
	}
	
	private static BufferedImage createTransformed(BufferedImage image, AffineTransform at)
	    {
	        BufferedImage newImage = new BufferedImage(
	            image.getWidth(), image.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g = newImage.createGraphics();
	        g.transform(at);
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	        return newImage;
	    }
	
	private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }
	
	public static BufferedImage getGameObjectbyID(String tag, int ID) {
		String path = "resources\\image\\Game\\"+tag+"\\"+ID+".png";
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
}
