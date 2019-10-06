package Network.Objects;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.FatalException;
import Network.Engine.JsonFormattable;

public class WaitingRoomInfo extends JsonFormattable{
	private String roomName;
	private String roomIP;
	private String roomPW;
	private String roomMasterName;

	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Chat> chats = new ArrayList<>();
	
	public WaitingRoomInfo(String roomName, String roomIP, String pw, String masterName) {
		super();
		this.roomName = roomName;
		this.roomIP = roomIP;
		this.roomMasterName = masterName;
		this.roomPW = pw;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public String getRoomPW() {
		return roomPW;
	}
	
	public void setRoomPW(String roomPW) {
		this.roomPW = roomPW;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomIP() {
		return roomIP;
	}
	public void setRoomIP(String roomIP) {
		this.roomIP = roomIP;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void deletePlayer(Player p) {
		players.remove(p);
	}
	
	public int getPlayerNum() {
		return players.size();
	}
	
	public String getRoomMasterName() {
		return roomMasterName;
	}

	public void setRoomMasterName(String roomMasterName) {
		this.roomMasterName = roomMasterName;
	}

	public ArrayList<Chat> getChats() {
		return chats;
	}
	
	public void addChat(Chat chat) {
		chats.add(chat);
	}
}
