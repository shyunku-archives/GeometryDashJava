package Map.Core;

import java.io.File;

import Core.Functions;

public class Map{
	//해당 맵의 모든 정보
	public static final int DEFAULT_GRID_SIZE = 50;
	
	private GameEnvironment environment;
	private MapHeader header;
	private MapData mapData;
	
	public Map(File folder) {
		environment = new GameEnvironment();
		
	}

	public GameEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(GameEnvironment environment) {
		this.environment = environment;
	}

	public MapHeader getHeader() {
		return header;
	}

	public void setHeader(MapHeader header) {
		this.header = header;
	}

	public MapData getMapData() {
		return mapData;
	}

	public void setMapData(MapData mapData) {
		this.mapData = mapData;
	}
	
	
}
