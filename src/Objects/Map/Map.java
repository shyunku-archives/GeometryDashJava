package Objects.Map;

import java.io.File;

import Core.Functions;

public class Map{
	private String mapName;
	private long createTime;
	private long recentUpdateTime;
	private String info;
	private File file;
	
	public Map(File file) {
		mapName = Functions.getFilenameNoExtension(file.getName());
		this.file = file;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getRecentUpdateTime() {
		return recentUpdateTime;
	}

	public void setRecentUpdateTime(long recentUpdateTime) {
		this.recentUpdateTime = recentUpdateTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
