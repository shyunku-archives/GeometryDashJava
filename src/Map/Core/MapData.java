package Map.Core;

import java.awt.Graphics2D;
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
	
	public void addBlock(Block block) {
		blocks.add(block);
	}
	
	public void addSpike(Spike spike) {
		spikes.add(spike);
	}
	
	public void addJumpBall(JumpBall jumpBall) {
		jumpBalls.add(jumpBall);
	}
	
	public void addDecoration(Decoration decoration) {
		decorations.add(decoration);
	}
	
	public void drawAll(Graphics2D g, double zoomRate) {
		for(Block block : blocks)
			block.draw(g, zoomRate);
		for(Spike spike : spikes)
			spike.draw(g, zoomRate);
		for(JumpBall jumpBall : jumpBalls)
			jumpBall.draw(g, zoomRate);
		for(Decoration decoration : decorations)
			decoration.draw(g, zoomRate);
	}
}
