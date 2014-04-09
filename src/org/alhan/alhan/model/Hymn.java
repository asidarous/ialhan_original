package org.alhan.alhan.model;

public class Hymn {

	private String id;
	private String name;
	

	public Hymn(String hymnId, String hymnName) {
		this.id = hymnId;
		this.name = hymnName;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
