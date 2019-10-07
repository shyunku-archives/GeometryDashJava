package Objects.Map;

public class ZoomEngine {
	private double[] zoomStage;
	private int zoomIndex;
	
	public ZoomEngine(double[] stage, int initial) {
		zoomStage = stage;
		zoomIndex = initial;
		if(initial<0||initial>zoomStage.length-1)
			throw new ArrayIndexOutOfBoundsException();
	}
	
	public double zoomIn() {
		int original = zoomIndex;
		zoomIndex--;
		if(zoomIndex<0)
			zoomIndex = 0;
		return zoomStage[zoomIndex]/zoomStage[original];
	}
	
	public double zoomOut() {
		int original = zoomIndex;
		zoomIndex++;
		if(zoomIndex>zoomStage.length-1)
			zoomIndex = zoomStage.length-1;
		return zoomStage[zoomIndex]/zoomStage[original];
	}
	
	public double getCurZoomRate() {
		return zoomStage[zoomIndex];
	}
}
