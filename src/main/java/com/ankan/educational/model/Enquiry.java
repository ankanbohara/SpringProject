package com.ankan.educational.model;

public class Enquiry {
	private int id;
	
	
	private String name;
	
	
	
	private String email;
	
	private String address;
	
//	@NotEmpty(message="required")
//	@Pattern(regexp="(^[0-9]{10})",message="Must be 10 digits")
	private String contact;
	
//	@NotEmpty(message="required")
	private String description;
	
	private boolean responded;
	
	/*Constructors*/
	
	public Enquiry() {
	}
	public Enquiry(int id, String name, String email, String address, String contact, String description,
			boolean responded) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.contact = contact;
		this.description = description;
		this.responded = responded;
	}
	
	/*Getters and Setters*/
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isResponded() {
		return responded;
	}
	public void setResponded(boolean responded) {
		this.responded = responded;
	}
	
	
}
