package org.alhan.alhan.model;

public class Hymn {

	

	private String hymnCoptic;
	private String hymnEnglish;
	
	private String id;
	private String name;

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getHymnCoptic() {
		return hymnCoptic;
	}
	public void setHymnCoptic(String hymnCoptic) {
		this.hymnCoptic = hymnCoptic;
	}
	public String getHymnEnglish() {
		return hymnEnglish;
	}
	public void setHymnEnglish(String hymnEnglish) {
		this.hymnEnglish = hymnEnglish;
	}

	public Hymn(String hymnId, String hymnName) {
		this.id = hymnId;
		this.name = hymnName;
	}


}
