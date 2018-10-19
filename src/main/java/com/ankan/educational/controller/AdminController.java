package com.ankan.educational.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.EnquiryDAO;
import com.ankan.educational.model.Enquiry;
import com.ankan.educational.model.User;

@Controller
public class AdminController {

	
	@Autowired
	EnquiryDAO enquirydao;
	
	@RequestMapping("/admin")
	public ModelAndView adminHome(HttpSession session)
	{
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = new ModelAndView("admin");
		model.addObject("username",(String)session.getAttribute("username"));
		return model;
	}
	
	@RequestMapping("/admin/viewEnquiry")
	public ModelAndView enquiries(HttpSession session)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = new ModelAndView("ViewEnquiries");
		List<Enquiry>enquiries = enquirydao.list();
		model.addObject("enquiries",enquiries);
		model.addObject("username",(String)session.getAttribute("username"));
		return model;
	}
	
	@RequestMapping("/admin/viewEnquiry/update/{enquiryid}")
	public ModelAndView enquiriesupdate(HttpSession session,@PathVariable("enquiryid") int id)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		enquirydao.toggleEnquiry(id);
		ModelAndView model = new ModelAndView("ViewEnquiries");
		List<Enquiry>enquiries = enquirydao.list();
		model.addObject("enquiries",enquiries);
		model.addObject("username",(String)session.getAttribute("username"));
		return model;
	}
	
	@RequestMapping("/admin/viewEnquiry/delete/{enquiryid}")
	public ModelAndView enquiriesdelete(HttpSession session,@PathVariable("enquiryid") int id)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		enquirydao.delete(id);
		ModelAndView model = new ModelAndView("ViewEnquiries");
		List<Enquiry>enquiries = enquirydao.list();
		model.addObject("enquiries",enquiries);
		model.addObject("username",(String)session.getAttribute("username"));
		return model;
	}
}
