package com.ankan.educational.model;

public class Material {
	private int id;
	private String name;
	private int topicid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public Material(int id, String name, int topicid) {
		this.id = id;
		this.name = name;
		this.topicid = topicid;
	}
	public Material() {
		// TODO Auto-generated constructor stub
	}
	

}
