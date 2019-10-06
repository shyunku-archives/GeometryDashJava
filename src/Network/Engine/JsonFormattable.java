package Network.Engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public abstract class JsonFormattable {
	public JsonElement getJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return gson.toJsonTree(this);
	}
}
