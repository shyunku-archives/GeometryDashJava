package Objects;

public class VirtualScroller {
	private int yScroll;
	private int minY = 0;
	private int maxY = 0;
	
	public VirtualScroller() {
		yScroll = 0;
	}
	
	public void setThis(int min, int max) {
		this.minY = min;
		if(max<min)max = min;
		this.maxY = max;
	}
	
	public void scroll(int rotate) {
		this.yScroll += rotate;
		if(yScroll<minY)yScroll = minY;
		if(yScroll>maxY)yScroll = maxY;
	}
	
	public int getCoordinate(double offset) {
		return (int)(yScroll*offset);
	}
}
