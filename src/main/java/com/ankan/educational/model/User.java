package com.ankan.educational.model;

import javax.servlet.http.HttpSession;



public class User{
	
	private int userid;
	
	
	private String username;
	

	private String password;
	
	private boolean enabled;
	

	private String email;
	
	
	private String address;
	

	private String contact;
	
	private String authority;

	
	

	public User() {
		
		// TODO Auto-generated constructor stub
	}

	
	
	public User(int userid, String username, String password, boolean enabled, String email, String address,
			String contact, String authority) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.address = address;
		this.contact = contact;
		this.authority = authority;
	}



	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	public static boolean isValid(HttpSession session)
	{
		return ((String)session.getAttribute("username")!=null && (String)session.getAttribute("password")!=null);
	}
}
