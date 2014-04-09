package org.alhan.alhan.model;

public class Hymn {

	
//	private String hymnName;
//	private String hymnID;
	private String hymnCoptic;
	private String hymnEnglish;
	
	private String id;
	private String name;
	


//	public String getHymnName() {
//		return hymnName;
//	}
//	public void setHymnName(String hymnName) {
//		this.hymnName = hymnName;
//	}
//	public String getHymnID() {
//		return hymnID;
//	}
//	public void setHymnID(String hymnID) {
//		this.hymnID = hymnID;
//	}
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
