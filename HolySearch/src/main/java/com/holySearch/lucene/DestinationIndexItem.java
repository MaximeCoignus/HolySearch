package com.holySearch.lucene;

public class DestinationIndexItem {
	private int destinationId;
	private String destinationFrenchName;
	public static final String DESTINATIONID = "destinationId";
	public static final String DESTINATIONFRENCHNAME = "destinationFrenchName";

	public DestinationIndexItem(int destinationId, String destinationFrenchName) {
		this.destinationId = destinationId;
		this.destinationFrenchName = destinationFrenchName;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public String getDestinationFrenchName() {
		return destinationFrenchName;
	}

	@Override
	public String toString() {
		return "IndexItem{" + "destinationId=" + destinationId + ", destinationFrenchName=" + destinationFrenchName + '}';
	}
}
