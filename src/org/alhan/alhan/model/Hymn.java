package org.alhan.alhan.model;

public class Hymn {

	

	private String hymnCoptic;
	private String hymnEnglish;
	
	private String id;
	private String name;
	private String desc;

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
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Hymn(String hymnId, String hymnName, String hymnDesc) {
		this.id = hymnId;
		this.name = hymnName;
		this.setDesc(hymnDesc);
	}



}
