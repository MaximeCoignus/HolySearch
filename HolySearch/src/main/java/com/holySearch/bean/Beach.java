package com.holySearch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beaches")
public class Beach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int beachId;
	
	@Column(name = "beach_name")
	private String beachName;
	
	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;
	
	@Column(name = "address")
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
