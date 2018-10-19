package com.ankan.educational.model;

public class Enrollment {
private int id;
private int doe;
private String invoice;
private int courseid;
private int userid;

public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public Enrollment() {

}

public Enrollment(int id, int doe, String invoice, int courseid, int userid) {
	super();
	this.id = id;
	this.doe = doe;
	this.invoice = invoice;
	this.courseid = courseid;
	this.userid = userid;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDoe() {
	return doe;
}
public void setDoe(int doe) {
	this.doe = doe;
}
public String getInvoice() {
	return invoice;
}
public void setInvoice(String invoice) {
	this.invoice = invoice;
}
public int getCourseid() {
	return courseid;
}
public void setCourseid(int courseid) {
	this.courseid = courseid;
}
}
