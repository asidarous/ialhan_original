package org.alhan.alhan.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Event {

	private String name;
	private String id;
	private List<Hymn> hymns;
	
	public Event(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.hymns = new ArrayList<Hymn>();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void addHymns(Hymn... hymns) {
		List<Hymn> hymnList  = Arrays.asList(hymns);
		this.hymns.addAll(hymnList);
	}
	
	public void addHymn(String hymnId, String hymnName) {
		Hymn hymn = new Hymn(hymnId, hymnName);
		addHymns(hymn);
	}
	
	public List<Hymn> getHymns() {
		return Collections.unmodifiableList(hymns);
	}
	
}
