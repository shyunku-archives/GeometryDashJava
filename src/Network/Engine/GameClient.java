package Network.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Core.Functions;
import Core.Global;
import Managers.ManagerManager;
import Network.Objects.Player;
import Network.Objects.WaitingRoomInfo;

public class GameClient {
	private Socket socket;
	private PrintWriter writer;
	private Player player;
	
	private long pingID = 0;
	private HashMap<Long, Long> pingEngine = new HashMap<>();
	
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
							//f.print("[Client <- Server] Message Received :\n"+pretty);
							int tag = jgen.getTag(response);
							
							if(tag!= NetworkManager.WAITING_ROOM_INFO_LOAD)
								if(tag!= NetworkManager.PING_TEST)
									f.print("[Client <- Server] Message Received :\n"+pretty);
							
							JsonObject body = new JsonParser().parse(response).getAsJsonObject().get("body").getAsJsonObject();
							
							switch(tag) {
							case NetworkManager.PARTICIPATE_REFUSE_WRONG_PASSWORD:
								//비번 틀림
								break;
							case NetworkManager.PARTICIPATE_REFUSE_ROOM_FULL:
								//방 꽉참
								break;
							case NetworkManager.PING_TEST:
								long id = body.get("id").getAsLong();
								long time = body.get("time").getAsLong();
								long now = System.nanoTime();
								Global.ping = now - time;
								break;
							case NetworkManager.PARTICIPATE_ACCPETED:
								break;
							case NetworkManager.WAITING_ROOM_INFO_LOAD:
								roomInfo = new Gson().fromJson(body, WaitingRoomInfo.class);
								break;
							}
						}
					} catch (SocketException e) {
						//서버장이 나감
						ManagerManager.pm.GoToSelectMultiplayModePanel();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true) {
						try {
							long currentPing = System.nanoTime();
							long currentID = pingID++;
							
							registerPing(currentID, currentPing);
							
							sendMessageToServer(jgen.bindAll(NetworkManager.PING_TEST, player.getPlayerNickname(), jgen.getPingData(currentID, currentPing)));
							
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (ConnectException e) {
			//서버에 연결 실패
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void registerPing(long id, long cur) {
		pingEngine.put(id, cur);
	}
	
	private long getPingDiff(long id) {
		long diff = pingEngine.get(id) - System.nanoTime();
		pingEngine.remove(id);
		return diff;
	}
	
	public void sendMessageToServer(String msg){
		writer.println(msg);
		writer.flush();
	}
}
