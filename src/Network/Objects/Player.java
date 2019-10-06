package Network.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import Network.Engine.JsonFormattable;

public class Player extends JsonFormattable{
	private String playerNickname;
	private String ipAddress;
	
	public Player(String playerNickname, String ipAddress) {
		super();
		this.playerNickname = playerNickname;
		this.ipAddress = ipAddress;
	}
	
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
