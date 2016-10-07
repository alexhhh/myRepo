package com.ikon.alexx.model;

public class AreaSearchCriteria {

	private double minLat;
	private double maxLat;
	private double minLng;
	private double maxLng;

 
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
	public double getMinLng() {
		return minLng;
	}
	public void setMinLng(double minLng) {
		this.minLng = minLng;
	}
	public double getMaxLng() {
		return maxLng;
	}
	public void setMaxLng(double maxLng) {
		this.maxLng = maxLng;
	}
 
	@Override
	public String toString() {
		return "AreaSearchCriteria [minLat=" + minLat + ", maxLat=" + maxLat + ", minLng=" + minLng + ", maxLng="
				+ maxLng + "]";
	}
	
}
