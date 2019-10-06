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
	
	public void zoomIn() {
		zoomIndex--;
		if(zoomIndex<0)
			zoomIndex = 0;
	}
	
	public void zoomOut() {
		zoomIndex++;
		if(zoomIndex>zoomStage.length-1)
			zoomIndex = zoomStage.length-1;
	}
	
	public double getCurZoomRate() {
		return zoomStage[zoomIndex];
	}
}
