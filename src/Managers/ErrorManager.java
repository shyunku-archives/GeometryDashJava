package Managers;

import java.util.HashMap;

public class ErrorManager {
	public static final int NO_ERROR = 0;
	public static final int IP_ADDRESS_EMPTY_ERROR = -1;
	public static final int IP_ADDRESS_WRONG_FORMAT = -2;
	private static HashMap<Integer, String> mBundle = new HashMap<>();
	
	public ErrorManager() {
		addError(this.NO_ERROR, "");
		addError(this.IP_ADDRESS_EMPTY_ERROR, "Please Fill IP Address Field");
		addError(this.IP_ADDRESS_WRONG_FORMAT, "Please Fill IP Address with Right Format (ex. 142.23.5.9)");
	}
	
	public void addError(int id, String msg) {
		mBundle.put(id, msg);
	}
	
	public static String getErrorMsg(int id) {
		return mBundle.get(id);
	}
}
