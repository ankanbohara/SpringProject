package com.ankan.educational.model;

public class Section {

	private int sectionid;
	private int courseid;
	private String name;
	private String description;
	
	public Section() {
		// TODO Auto-generated constructor stub
	}

	public Section(int sectionid, int courseid, String name, String description) {
		super();
		this.sectionid = sectionid;
		this.courseid = courseid;
		this.name = name;
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
	
}
