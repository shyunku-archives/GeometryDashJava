package Network.Engine;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Core.Functions;
import Network.Objects.Player;

public class JsonDataGenerator {
	public JsonElement getPingData(long id, long time) {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("time", time);
		return json;
	}
	
	public JsonElement getEmptyData() {
		JsonObject json = new JsonObject();
		return new Gson().fromJson(json, JsonElement.class);
	}
	
	public JsonElement getParticipatingServerData(Player p, String pw) {
		JsonObject json = new JsonObject();
		json.add("player", p.getJson());
		json.addProperty("password", pw);
		return json;
	}
	
	public String bindAll(int id, String sender, JsonElement body) {
		Gson gson = new Gson();
		JsonObject data = new JsonObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS");
		
		JsonObject head = new JsonObject();
		head.addProperty("tag", id);
		head.addProperty("sender", sender);
		head.addProperty("timestamp", sdf.format(new Date(System.currentTimeMillis())));
		
		data.add("head", gson.fromJson(head, JsonElement.class));
		data.add("body", body);
		
		return bind(data);
	}
	
	public static JsonObject ElementToObject(JsonElement element) {
		Gson gson = new Gson();
		return gson.fromJson(element, JsonObject.class);
	}
	
	public static String bind(JsonObject object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	public static String toPrettyFormat(String str) {
		JsonElement json = new JsonParser().parse(str);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(json);
	}
	
	public int getTag(String msg) {
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(msg).getAsJsonObject();
		int tag = root.get("head").getAsJsonObject().get("tag").getAsInt();
		
		return tag;
	}
}
