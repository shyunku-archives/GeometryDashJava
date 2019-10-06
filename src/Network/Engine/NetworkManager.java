package Network.Engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Exceptions.FatalException;

public class NetworkManager {
	public static final String LOCALHOST = "127.0.0.1";
	public static final int SERVER_PORT = 10300;
	public static final int MAX_CAPACITY = 6;
	
	public static final int PARTICIPATING_SERVER = 2000;
	public static final int PARTICIPATE_REFUSE_WRONG_PASSWORD = 2001;
	public static final int PARTICIPATE_REFUSE_ROOM_FULL = 2002;
	public static final int PARTICIPATE_ACCPETED = 2003;
	public static final int WAITING_ROOM_INFO_LOAD = 2004;
	public static final int NORMAL_CHAT_SEND = 2005;
	public static final int SYSTEMIC_CHAT_SEND = 2006;
	private HashMap<Integer, String> messageBundle = new HashMap<>();
	
	public NetworkManager() {
		addMessage(this.PARTICIPATING_SERVER, "PARTICIPATING_SERVER_MSG");
		addMessage(this.PARTICIPATE_REFUSE_WRONG_PASSWORD, "PARTICIPATE_REFUSE_WRONG_PASSWORD");
		addMessage(this.PARTICIPATE_REFUSE_ROOM_FULL, "PARTICIPATE_REFUSE_ROOM_FULL");
		addMessage(this.PARTICIPATE_ACCPETED, "PARTICIPATE_ACCEPTED");
		addMessage(this.NORMAL_CHAT_SEND, "NORMAL_CHAT_SEND");
		addMessage(this.SYSTEMIC_CHAT_SEND, "SYSTEMIC_CHAT_SEND");
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
