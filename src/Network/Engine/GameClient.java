package Network.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Core.Functions;
import Network.Objects.Player;
import Network.Objects.WaitingRoomInfo;

public class GameClient {
	private Socket socket;
	private PrintWriter writer;
	private Player player;
	
	Functions f = new Functions();
	NetworkManager nm = new NetworkManager();
	
	//클라이언트 데이터
	private WaitingRoomInfo roomInfo = new WaitingRoomInfo("","", "", "");
	
	public WaitingRoomInfo getWaitingRoomInfo() {
		return this.roomInfo;
	}
	
	public GameClient(Player me) {
		player = me;
	}
	
	public void connect(String IP, String PW) {
		f.cprint("[Client]");
		socket = new Socket();
		JsonObject msg = new JsonObject();
		Gson gson = new Gson();
		JsonDataGenerator jgen = new JsonDataGenerator();
		
		try {
			socket.connect(new InetSocketAddress(IP, NetworkManager.SERVER_PORT));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			f.cprint("Successfully Connected to Server!");
			
			//비번 확인?
			
			//JSON MESSAGE CREATION
			sendMessageToServer(jgen.bindAll(NetworkManager.PARTICIPATING_SERVER, player.getPlayerNickname(), jgen.getParticipatingServerData(player, PW)));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new Thread(new Runnable() {
				@Override
				public void run() {
					String response = null;
					try {
						while(true) {
							response = reader.readLine();
							String pretty = JsonDataGenerator.toPrettyFormat(response);
							int tag = jgen.getTag(response);
							
							if(tag!= NetworkManager.WAITING_ROOM_INFO_LOAD)
								f.print("[Client <- Server] Message Received :\n"+pretty);
							
							JsonObject body = new JsonParser().parse(response).getAsJsonObject().get("body").getAsJsonObject();
							
							switch(tag) {
							case NetworkManager.PARTICIPATE_REFUSE_WRONG_PASSWORD:
								//비번 틀림
								break;
							case NetworkManager.PARTICIPATE_REFUSE_ROOM_FULL:
								//방 꽉참
								break;
							case NetworkManager.PARTICIPATE_ACCPETED:
								break;
							case NetworkManager.WAITING_ROOM_INFO_LOAD:
								roomInfo = new Gson().fromJson(body, WaitingRoomInfo.class);
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageToServer(String msg){
		writer.println(msg);
		writer.flush();
	}
}
