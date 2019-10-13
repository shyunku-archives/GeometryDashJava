package Map.Core;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Network.Engine.JsonFormattable;

public class MapHeader extends JsonFormattable{
	
	private String createTime;
	private String recentUpdate;
	private long version;
	private String mapName;
	
	public MapHeader(String mapName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		//initial
		this.recentUpdate = this.createTime = dateFormat.format(new Date(System.currentTimeMillis()));
		this.version = 0;
		this.mapName = mapName;
	}
	
	
	public MapHeader(String createTime, String recentUpdate, long version, String mapName) {
		super();
		this.createTime = createTime;
		this.recentUpdate = recentUpdate;
		this.version = version;
		this.mapName = mapName;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRecentUpdate() {
		return recentUpdate;
	}
	public void setRecentUpdate(String recentUpdate) {
		this.recentUpdate = recentUpdate;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public JsonElement getJsonElement() {
		JsonObject json = new JsonObject();
		json.addProperty("mapName", this.mapName);
		json.addProperty("version", this.version);
		json.addProperty("createTime", this.createTime);
		json.addProperty("recentUpdate", this.recentUpdate);
		
		return json;
	}
}
