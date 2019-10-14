package Map.Engines;

import java.awt.image.BufferedImage;

import Core.Functions;

public class PlayerSkinImage {
	//인게임 들어가기 직전에만 실행할 것
	private BufferedImage normalSkin;
	//private BufferedImage flightSkin;
	//스킨 종류가 많아지면 인자도 많아짐
	public PlayerSkinImage(int normalID) {
		this.normalSkin = Functions.getPlayerSkinImage("normal", normalID);
	}
}
