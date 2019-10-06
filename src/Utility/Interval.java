package Utility;

public class Interval {
	private int[] interval;
	
	public Interval(int[] list) {
		this.interval = list;
	}
	
	public String toFormat(double cur) {
		for(int i=0;i<interval.length;i++)
			if(cur<interval[i])
				return "<"+String.format("%3d", interval[i]);
		return ">"+String.format("%3d", interval[interval.length-1]);
	}
}
