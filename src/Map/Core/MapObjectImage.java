package Map.Core;

import Exceptions.FatalException;

public class MapObjectImage {
	public static final int TYPE_ALL = -90;
	
	public static final int TYPE_BLOCK = 100;
	public static final int TYPE_SPIKE = 101;
	public static final int TYPE_JUMPBALL = 102;
	public static final int TYPE_DECORATION = 103;
	
	public static String typeIntegerToString(int type) {
		switch(type) {
		case TYPE_BLOCK: return "Block";
		case TYPE_SPIKE: return "Spike";
		case TYPE_JUMPBALL: return "JumpBall";
		case TYPE_DECORATION: return "Decoration";
		}
		return null;
	}
	
	public static int typeStringToTag(String name) {
		if(name.equals("Block")) return TYPE_BLOCK;
		if(name.equals("Spike")) return TYPE_SPIKE;
		if(name.equals("JumpBall")) return TYPE_JUMPBALL;
		if(name.equals("Decoration")) return TYPE_DECORATION;
		
		new FatalException().throwThis();
		return -1;
	}
	
	//BLOCKS
	public static final int DEFAULT_BLOCK = 0;
	
	//SPIKES
	public static final int DEFAULT_SPIKE = 0;
}
