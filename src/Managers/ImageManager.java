package Managers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Core.Functions;

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
	public static final int MY_LEVELS_LEFT_SIDE_BAR = 2018;
	public static final int MY_LEVELS_RIGHT_SIDE_BAR = 2019;
	public static final int GO_BACK_PINK_BUTTON = 2020;
	public static final int VIEW_BUTTON = 2021;
	public static final int EDIT_MY_LEVEL_BUTTON = 2022;
	public static final int TEST_MY_LEVEL_BUTTON = 2023;
	public static final int BGM_SELECT_BUTTON = 2024;
	
	public HashMap<Integer, BufferedImage> imageBundle = new HashMap<>();
	
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
		putImage(MY_LEVELS_LEFT_SIDE_BAR, "side_bar.png");
		putImage(MY_LEVELS_RIGHT_SIDE_BAR, "side_bar.png", 180);
		putImage(GO_BACK_PINK_BUTTON, "go_back_pink_button.png", 71, 80);
		putResizedImage(VIEW_BUTTON, "green_template_button.png", 0.35f);
		putResizedImage(EDIT_MY_LEVEL_BUTTON, "edit_button.png", 0.6f);
		putResizedImage(TEST_MY_LEVEL_BUTTON, "play_button.png", 0.6f);
		putResizedImage(BGM_SELECT_BUTTON, "folder_image.png", 0.4f);
	}
	
	public static String getImagePath(String filename) {
		return "resources\\image\\"+filename;
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
	

}
