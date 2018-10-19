package com.ankan.educational.model;

public class Test {
	
	private int id;
	private String description;
	private int duration;
	private int maxScore;
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Test(int id, String description, int duration, int maxScore) {
		this.id = id;
		this.description = description;
		this.duration = duration;
		this.maxScore = maxScore;
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getMaxScore() {
		return maxScore;
	}


	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}


	
}
