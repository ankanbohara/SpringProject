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
import com.ankan.educational.model.Topic;
import com.ankan.educational.model.User;

@Controller
public class AdminTopicController {

	@Autowired
	public CourseDAO coursedao;

	@Autowired
	public EnrollmentDAO enrollmentdao;

	@Autowired
	public TopicDAO topicdao;

	@Autowired
	public SectionDAO sectiondao;

	public ModelAndView addAttributes(int sectionid, int courseid) {

		ModelAndView model = new ModelAndView("TopicCreation");
		model.addObject("topic", new Topic());
		model.addObject("topics", topicdao.list(sectionid, courseid));
		for (Topic t : topicdao.list(sectionid, courseid)) {
			System.out.println(t.getId());
			System.out.println(t.getName());
			System.out.println(t.getDescription());
		}
		model.addObject("courseid", courseid);
		model.addObject("sectionid", sectionid);
		model.addObject("courseName", coursedao.get(courseid).getName());
		model.addObject("sectionName", sectiondao.get(sectionid, courseid).getName());
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}", method = RequestMethod.GET)
	public ModelAndView sectionCreation(@PathVariable("sectionid") int sectionid,
			@PathVariable("courseid") int courseid,HttpSession session) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = addAttributes(sectionid, courseid);
		System.out.println("HI ANKAN BOHARA");
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/topicsave", method = RequestMethod.POST)
	public ModelAndView topicSuccess(@PathVariable("courseid") int courseid, @PathVariable("sectionid") int sectionid,
			@ModelAttribute("topic") Topic topic, BindingResult result,HttpSession session) {
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		if (result.hasErrors()) {

			return addAttributes(sectionid, courseid);
		}
		ModelAndView model = new ModelAndView("redirect:/admin/courses/" + courseid + "/" + sectionid);
//		if (update != null) {
//			topicdao.update(topic);
//			return model;
//		}
//		topicdao.insert(topic);
		topicdao.saveorUpdate(topic);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/topicupdate/{topicid}",method=RequestMethod.GET)
	public ModelAndView sectionUpdate(@ModelAttribute("topic") Topic topic, @PathVariable("courseid") int courseid,
			@PathVariable("sectionid") int sectionid,@PathVariable("topicid") int topicid,HttpSession session) {
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		ModelAndView model = addAttributes(sectionid, courseid);
		model.addObject("update", "update");
		model.addObject("tid",topicid);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/topicdelete/{topicid}")
	public ModelAndView topicDelete(@PathVariable("topicid") int topicid, @PathVariable("courseid") int courseid,
			@PathVariable("sectionid") int sectionid) {

		List<Integer> materialContainingTopics = topicdao.containMaterial();
		if (materialContainingTopics.contains(topicid)) {
			ModelAndView model = addAttributes(sectionid, courseid);
			model.addObject("error", "Can't be deleted. First delete the material associated with topic. You might have created any test. First delete the test from the test section on the main page.");
			return model;
		}
		topicdao.delete(topicid);
		ModelAndView model = addAttributes(sectionid, courseid);
		return model;
	}

}
