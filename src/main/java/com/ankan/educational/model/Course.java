package com.ankan.educational.model;

public class Course {
	
	
	private int id;
	
	
	private String name;
	
	private String description;
	
	
	
	private boolean active;
	

	
	private int price;

	private int noOfEnrolled;
	public int getNoOfEnrolled() {
		return noOfEnrolled;
	}

	public void setNoOfEnrolled(int noOfEnrolled) {
		this.noOfEnrolled = noOfEnrolled;
	}

	public Course() {

	}

	public Course(int id, String name, String description, int price, boolean active,int noOfEnrolled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.noOfEnrolled = noOfEnrolled;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
