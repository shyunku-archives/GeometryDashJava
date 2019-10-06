package Objects;

import java.awt.Color;
import java.awt.Graphics2D;

import Core.Functions;
import Managers.ErrorManager;

public class ErrorMessager {
	private int curState = ErrorManager.NO_ERROR;
	
	public void setError(int flag) {
		curState = flag;
	}
	
	public String getStateMsg() {
		return ErrorManager.getErrorMsg(this.curState);
	}
	
	public boolean isNormal() {
		return curState == ErrorManager.NO_ERROR;
	}
	
	public int getStringWidth(Graphics2D g, float size) {
		return Functions.getFontWidth(g, this.getStateMsg(), size);
	}
}
