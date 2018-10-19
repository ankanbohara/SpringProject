package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Enquiry;

public interface EnquiryDAO {

	public void saveOrUpdate(Enquiry enquiry);
	public void delete(int id);
	public Enquiry get(int id);
	public List<Enquiry>list();
	public void toggleEnquiry(int id);
}
