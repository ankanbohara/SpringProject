package com.ankan.educational.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.CourseDAO;
import com.ankan.educational.dao.EnrollmentDAO;
import com.ankan.educational.dao.SectionDAO;
import com.ankan.educational.dao.TopicDAO;
import com.ankan.educational.model.Section;
import com.ankan.educational.model.User;

@Controller
public class AdminSectionController {

	@Autowired
	public CourseDAO coursedao;

	@Autowired
	public EnrollmentDAO enrollmentdao;

	@Autowired
	public SectionDAO sectiondao;
	
	@Autowired
	public TopicDAO topicdao;

	public ModelAndView addAttributes(int courseid) {

		ModelAndView model = new ModelAndView("SectionCreation");
		model.addObject("section", new Section());
		model.addObject("sections", sectiondao.list(courseid));
		model.addObject("coursename",coursedao.get(courseid).getName());
		model.addObject("courseid",courseid);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}", method = RequestMethod.GET)
	public ModelAndView sectionCreation(@PathVariable("courseid") int courseid,HttpSession session) {

		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		return addAttributes(courseid);
	}

	@RequestMapping(value = "/admin/courses/{courseid}/sectionsave", method = RequestMethod.POST)
	public ModelAndView sectionSuccess(@PathVariable("courseid") int courseid,
			@ModelAttribute("section") Section section, BindingResult result,HttpSession session) {
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		if (result.hasErrors()) {

			return addAttributes(courseid);
		}

		sectiondao.saveOrUpdate(section);
		return new ModelAndView("redirect:/admin/courses/" + courseid);

	}

	@RequestMapping(value = "/admin/courses/{courseid}/sectionupdate/{sectionid}")
	public ModelAndView sectionUpdate(@PathVariable("courseid") int courseid,@PathVariable("sectionid") int sectionid,HttpSession session) {
		ModelAndView model = addAttributes(courseid);
		model.addObject("update", "update");
		model.addObject("sectionid",sectionid);
		return model;
	}

	@RequestMapping(value="/admin/courses/{courseid}/sectiondelete/{sectionid}")
	public ModelAndView sectionDelete(@PathVariable("sectionid") int sectionid,@PathVariable("courseid") int courseid,HttpSession session)
	{
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		List<Integer>sectionIDContainingTopics = sectiondao.containTopic(sectionid, courseid);
		for(int u:sectionIDContainingTopics)
		{
			System.out.println(u);
		}
		if(sectionIDContainingTopics.contains(sectionid))
		{
			ModelAndView model = addAttributes(courseid);
			model.addObject("error","Can't be deleted. First delete the topics");
			return model;
		}
		sectiondao.delete(sectionid, courseid);
		ModelAndView model = addAttributes(courseid);
		return model;
	}
}
