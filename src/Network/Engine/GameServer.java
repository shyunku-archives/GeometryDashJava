package Network.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Core.Functions;
import Network.Objects.Player;
import Network.Objects.WaitingRoomInfo;

public class GameServer {
	private ServerSocket serverSocket = null;
	private HashMap<String, PrintWriter> writermap;
	Functions f = new Functions();
	
	//서버 데이터
	private WaitingRoomInfo roomInfo = new WaitingRoomInfo("", "", "", "");
	
	public GameServer(WaitingRoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}
	
	public void start() {
		f.cprint("[Server]");
		JsonDataGenerator jgen = new JsonDataGenerator();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(NetworkManager.SERVER_PORT);
					writermap = new HashMap<String, PrintWriter>();
					while(true) {
						Socket socket = serverSocket.accept();
						new Thread(new Runnable() {
							@Override
							public void run() {
								while(true) {
									try {
										BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
										PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
										String request = bufferedReader.readLine();
										String pretty = JsonDataGenerator.toPrettyFormat(request);
										f.print("[Server <- Client] Message Received :\n"+pretty);
										
										int tag = jgen.getTag(request);
										JsonObject body = new JsonParser().parse(request).getAsJsonObject().get("body").getAsJsonObject();
										
										switch(tag) {
										case NetworkManager.PARTICIPATING_SERVER:
											String acceptedPW = body.get("password").getAsString();
											String sender = body.get("player").getAsJsonObject().get("playerNickname").getAsString();
											if(!roomInfo.getRoomPW().equals(acceptedPW)) {
												PrintWriter temp = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
												temp.println(jgen.bindAll(NetworkManager.PARTICIPATE_REFUSE, roomInfo.getRoomMasterName(), jgen.getEmptyData()));
												temp.flush();
												temp.close();
												socket.close();
												break;
											}
											Player newPlayer = new Gson().fromJson(body.get("player").getAsJsonObject(), Player.class);
											roomInfo.addPlayer(newPlayer);
											addWriter(socket, sender);
											broadcast(sender, jgen.bindAll(NetworkManager.PARTICIPATE_ACCPETED, roomInfo.getRoomMasterName(), jgen.getEmptyData()));
											broadcast(jgen.bindAll(NetworkManager.WAITING_ROOM_INFO_LOAD, roomInfo.getRoomMasterName(), roomInfo.getJson()));
											break;
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}).start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(serverSocket != null && !serverSocket.isClosed())
						try {serverSocket.close();} catch (IOException e) {e.printStackTrace();}
				}
			}
		}).start();
	}
	
	private void removeWriter(String username) {
		synchronized(writermap) {
			writermap.remove(username);
		}
	}
	
	private void addWriter(Socket socket, String username) {
		synchronized(writermap) {
			try {
				writermap.put(username, new PrintWriter(new OutputStreamWriter(socket.getOutputStream())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void broadcast(String destination, String msg){
		synchronized (writermap) {
			PrintWriter writer = writermap.get(destination);
			writer.println(msg);
			writer.flush();
		}
	}
	
	public void broadcast(String msg) {
		try {
			synchronized (writermap) {
				Collection<PrintWriter> allUsers = writermap.values();
				Iterator<PrintWriter> i = allUsers.iterator();
				while (i.hasNext()) {
					PrintWriter p = i.next();
					p.println(msg);
					p.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}