package com.holySearch.transfert.object;

public class BeachBeanTO {

	private int beachId;
	
	private String beachName;
	
	private float latitude;
	
	private float longitude;
	
	private String address;
	
	public int getBeachId() {
		return beachId;
	}
	
	public String getBeachName() {
		return beachName;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setBeachId(int beachId) {
		this.beachId = beachId;
	}
	
	public void setBeachName(String beachName) {
		this.beachName = beachName;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}
