package com.holySearch.lucene;

public class BeachIndexItem {
	private int id;
	private String beachName;
	public static final String ID = "id";
	public static final String BEACHNAME = "beachName";

	public BeachIndexItem(int id, String beachName) {
		this.id = id;
		this.beachName = beachName;
	}

	public int getId() {
		return id;
	}

	public String getBeachName() {
		return beachName;
	}

	@Override
	public String toString() {
		return "IndexItem{" + "id=" + id + ", beachName='" + beachName + '}';
	}
}
