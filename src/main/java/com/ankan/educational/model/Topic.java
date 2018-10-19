package com.ankan.educational.model;

public class Topic {
	private int id;
	private String name;
	private String description;
	private int sectionid;
	private int courseid;
	
	public Topic() {
		
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Topic(int id, String name, String description, int sectionid, int courseid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.sectionid = sectionid;
		this.courseid = courseid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSectionid() {
		return sectionid;
	}
	public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
}
