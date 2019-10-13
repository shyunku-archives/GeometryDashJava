package Map.Engines;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import Core.Functions;
import Managers.ManagerManager;
import Map.Core.MapObjectImage;
import Utility.Pair;

public class GameObjectManager {
	private HashMap<Integer, Integer> objectNum = new HashMap<>();
	private HashMap<Integer, Pair<Integer, BufferedImage>> representImage = new HashMap<>();
	//index, type, image
	public GameObjectManager() {
		what(MapObjectImage.TYPE_BLOCK, "Block");
		what(MapObjectImage.TYPE_SPIKE, "Spike");
		what(MapObjectImage.TYPE_JUMPBALL, "JumpBall");
		what(MapObjectImage.TYPE_DECORATION, "Decoration");
		
		String path = "resources\\Image\\Game";
		File folder = new File(path);
		int counter = 0;
		for(final File entry : folder.listFiles()) {
			String foldername = entry.getName();
			int tag = MapObjectImage.typeStringToTag(foldername);
			BufferedImage img = ManagerManager.im.getGameObjectImage(tag, 0, 30, 30);
			
			representImage.put(counter, new Pair(tag, img));
			counter++;
		}
		
		objectNum.put(MapObjectImage.TYPE_ALL, counter);
	}
	
	private void what(int id, String real) {
		String path = "resources\\Image\\Game\\"+real;
		File folder = new File(path);
		int counter = 0;
		for(final File entry : folder.listFiles())
			counter++;
		
		objectNum.put(id, counter);
	}
	
	public Pair<Integer, BufferedImage> getRepresentType(int index){
		return representImage.get(index);
	}
	
	public int getNum(int id) {
		return objectNum.get(id);
	}
}
