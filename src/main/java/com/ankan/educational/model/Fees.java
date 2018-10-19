package com.ankan.educational.model;

public class Fees {

	private String invoice;
	private Long tid;
	private int uid;
	private double amount;
	
	public Fees() {

	}
	public Fees(String invoice, Long tid, int uid, double amount) {
		this.invoice = invoice;
		this.tid = tid;
		this.uid = uid;
		this.amount = amount;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
