package com.ankan.educational.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.CourseDAO;
import com.ankan.educational.dao.EnquiryDAO;
import com.ankan.educational.model.Enquiry;

@Controller
public class PageController {

	@Autowired
	public CourseDAO coursedao;
	
	@Autowired
	public EnquiryDAO enquirydao;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	/*Enquiry begins*/
	@RequestMapping(value="/enquiry",method=RequestMethod.GET)
	public String enquiry(Model model)	
	{
		model.addAttribute("getEnquiry",new Enquiry());
		return "Enquiry";
	}
	
	@RequestMapping(value="/enquiry/success",method=RequestMethod.POST)
	public String enquirysubmit(@ModelAttribute("getEnquiry") Enquiry enquiry,BindingResult result,Model model)
	{
		
		if(result.hasErrors())
		{
			return "Enquiry";
		}
		else
		{
			enquirydao.saveOrUpdate(enquiry);
			return "EnquirySuccess";
		}	
	}
	/*Enquiry Ends*/
	
	/*For login area*/
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String course(Model model)	
	{
		return "Users";
	}
	/*End login area*/
	
}
