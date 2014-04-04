package org.alhan.alhan.model;

public class Season {

	String seasonName;
	String seasonId;

	public Season(String seasonName, String seasonId) {
		super();
		this.seasonName = seasonName;
		this.seasonId = seasonId;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}


	@Override
	public String toString() {
		return seasonName;
	}
}
