package Map.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Core.Functions;
import Exceptions.FatalException;
import Network.Engine.JsonDataGenerator;
import Network.Engine.JsonFormattable;

public class Map{
	//ÇØ´ç ¸ÊÀÇ ¸ðµç Á¤º¸
	public static final int DEFAULT_GRID_SIZE = 50;
	
	private GameEnvironment environment;
	private MapHeader header;
	private MapData mapData;
	
	public Map(File folder) {
		//ÆÄ½Ì
		File datafile = new File(folder.getPath()+"\\data.json");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datafile));
			String line = null;
			StringBuilder builder = new StringBuilder();
			while((line = reader.readLine())!= null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
				
			String data = builder.toString();
			
			JsonObject dataObject = new JsonParser().parse(data).getAsJsonObject();
			JsonElement envData = dataObject.get("environment");
			JsonElement headData = dataObject.get("head");
			JsonElement bodyData = dataObject.get("body");
			
			
			environment = new Gson().fromJson(envData, GameEnvironment.class);
			header = new Gson().fromJson(headData, MapHeader.class);
			mapData = new Gson().fromJson(envData, MapData.class);
			
		} catch (IOException e) {
			new FatalException().throwThis();
		}
	}
	
	public Map(String mapName) {
		//initial
		environment = new GameEnvironment();
		header = new MapHeader(mapName);
		mapData = new MapData();
		
		//save
		File file = new File("AppData\\Maps\\"+mapName+"\\data.json");
		String data = getJson();
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out = new PrintWriter(file);
			out.print(data);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String getJson() {
		JsonDataGenerator jgen = new JsonDataGenerator();
		Gson gson = new Gson();
		JsonObject data = new JsonObject();
		
		data.add("head", header.getJson());
		data.add("body", mapData.getJson());
		data.add("environment", environment.getJson());
		
		String fin = jgen.bind(data);
		
		return JsonDataGenerator.toPrettyFormat(fin);
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
