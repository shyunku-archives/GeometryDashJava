package Map.Core;

import java.util.ArrayList;

import Map.Objects.Block;
import Map.Objects.Decoration;
import Map.Objects.JumpBall;
import Map.Objects.Spike;
import Network.Engine.JsonFormattable;

public class MapData extends JsonFormattable{
	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Spike> spikes = new ArrayList<>();
	private ArrayList<JumpBall> jumpBalls = new ArrayList<>();
	private ArrayList<Decoration> decorations = new ArrayList<>();
	
	public MapData() {
		super();
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	public ArrayList<JumpBall> getJumpBalls() {
		return jumpBalls;
	}
	public ArrayList<Decoration> getDecorations() {
		return decorations;
	}
}
