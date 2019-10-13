package Map.Core;

public class MapHeader {
	private String createTime;
	private String recentUpdate;
	private long version;
	private String mapName;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRecentUpdate() {
		return recentUpdate;
	}
	public void setRecentUpdate(String recentUpdate) {
		this.recentUpdate = recentUpdate;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	
}
