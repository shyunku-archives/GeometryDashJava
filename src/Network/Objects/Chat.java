package Network.Objects;

import Network.Engine.JsonFormattable;

public class Chat extends JsonFormattable{
	private String sender;
	private String content;
	private boolean isSystemic;
	
	public Chat(String sender, String content, boolean isSystemic) {
		super();
		this.sender = sender;
		this.content = content;
		this.isSystemic = isSystemic;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSystemic() {
		return isSystemic;
	}

	public void setSystemic(boolean isSystemic) {
		this.isSystemic = isSystemic;
	}
	
	
}
