package com.holySearch.lucene;

public class DestinationIndexItem {
	private int id;
	private String destinationFrenchName;
	public static final String ID = "id";
	public static final String DESTINATIONFRENCHNAME = "destinationFrenchName";

	public DestinationIndexItem(int id, String destinationFrenchName) {
		this.id = id;
		this.destinationFrenchName = destinationFrenchName;
	}

	public int getId() {
		return id;
	}

	public String getDestinationFrenchName() {
		return destinationFrenchName;
	}

	@Override
	public String toString() {
		return "IndexItem{" + "id=" + id + ", destinationFrenchName='" + destinationFrenchName + '}';
	}
}
