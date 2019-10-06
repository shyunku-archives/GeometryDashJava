package Network.Engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Exceptions.FatalException;

public class NetworkManager {
	public static final String LOCALHOST = "127.0.0.1";
	public static final int SERVER_PORT = 10300;
	
	public static final int PARTICIPATING_SERVER = 2000;
	public static final int PARTICIPATE_REFUSE = 2001;
	public static final int PARTICIPATE_ACCPETED = 2002;
	public static final int WAITING_ROOM_INFO_LOAD = 2003;
	private HashMap<Integer, String> messageBundle = new HashMap<>();
	
	public NetworkManager() {
		addMessage(this.PARTICIPATING_SERVER, "PARTICIPATING_SERVER_MSG");
		addMessage(this.PARTICIPATE_REFUSE, "PARTICIPATE_REFUSE");
		addMessage(this.PARTICIPATE_ACCPETED, "PARTICIPATE_ACCEPTED");
		addMessage(this.WAITING_ROOM_INFO_LOAD, "WAITING_ROOM_INFO_LOAD");
	}
	
	public void addMessage(int id, String msg) {
		messageBundle.put(id, msg);
	}
	
	public String getMessage(int id) {
		return messageBundle.get(id);
	}
	
	public int getIDbyMessage(String msg) {
		Set set = messageBundle.keySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			int id = (int)i.next();
			if(getMessage(id).equals(msg))
				return id;
		}
		new FatalException().throwThis();
		return 0;
	}
}
