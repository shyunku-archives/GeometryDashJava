package Map.Engines;

import java.awt.image.BufferedImage;

import Core.Functions;

public class PlayerSkinImage {
	//�ΰ��� ���� �������� ������ ��
	private BufferedImage normalSkin;
	//private BufferedImage flightSkin;
	//��Ų ������ �������� ���ڵ� ������
	public PlayerSkinImage(int normalID) {
		this.normalSkin = Functions.getPlayerSkinImage("normal", normalID);
	}
}
