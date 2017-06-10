package com.holySearch.lucene;

import com.google.gson.Gson;
import com.holySearch.transfert.object.DestinationItemTO;

public class DestinationIndexItem {
	private int destinationId;
	private String destinationJSON;
	public static final String DESTINATIONID = "destinationId";
	public static final String DESTINATIONJSON = "destinationJSON";

	public DestinationIndexItem(DestinationItemTO pDestinationTO) {
		this.destinationId = pDestinationTO.getDestinationId();
		Gson gson = new Gson();
		this.destinationJSON = gson.toJson(pDestinationTO);
	}

	public DestinationIndexItem(int destinationId, String destinationJSON) {
		this.destinationId = destinationId;
		this.destinationJSON = destinationJSON;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public String getDestinationJSON() {
		return destinationJSON;
	}

	@Override
	public String toString() {
		return "IndexItem{" + "destinationId=" + destinationId + ", destinationJSON=" + destinationJSON + '}';
	}
}
