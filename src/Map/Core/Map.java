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

public class Map extends JsonFormattable{
	//�ش� ���� ��� ����
	public static transient final int DEFAULT_GRID_SIZE = 50;
	
	private GameEnvironment environment;
	private MapHeader header;
	private MapData mapData;
	
	public Map(File folder) {
		//�Ľ�
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
			Map thismap = new Gson().fromJson(dataObject, Map.class);
			
			this.environment = thismap.environment;
			this.header = thismap.header;
			this.mapData = thismap.mapData;			
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
		File file = new File("AppData\\Maps\\Local\\"+mapName+"\\data.json");
		String data = getgJson();
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
	public void printJson() {
		String data = getgJson();
		Functions.print(data);
	}
	
	public void saveThis() {
		File file = new File("AppData\\Maps\\Local\\"+header.getMapName()+"\\data.json");
		String data = getgJson();
		
		try {
			PrintWriter out = new PrintWriter(file);
			out.flush();
			out.print(data);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getgJson() {
		JsonDataGenerator jgen = new JsonDataGenerator();
		Gson gson = new Gson();
		String str = gson.toJson(this.getJson());
		
		
		return jgen.toPrettyFormat(str);
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
