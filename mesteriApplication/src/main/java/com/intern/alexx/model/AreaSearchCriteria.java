package com.intern.alexx.model;

public class AreaSearchCriteria {

	private double minLat;
	private double maxLat;
	private double minLng;
	private double maxLng;

//	private Integer pageSize;
//	private Integer pageNumber;
	
	
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
//	public Integer getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(Integer pageSize) {
//		this.pageSize = pageSize;
//	}
//	public Integer getPageNumber() {
//		return pageNumber;
//	}
//	public void setPageNumber(Integer pageNumber) {
//		this.pageNumber = pageNumber;
//	}
	@Override
	public String toString() {
		return "AreaSearchCriteria [minLat=" + minLat + ", maxLat=" + maxLat + ", minLng=" + minLng + ", maxLng="
				+ maxLng + "]";
	}
	
}
