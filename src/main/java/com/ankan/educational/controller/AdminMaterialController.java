package com.ankan.educational.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.CourseDAO;
import com.ankan.educational.dao.EnrollmentDAO;
import com.ankan.educational.dao.MaterialDAO;
import com.ankan.educational.dao.QuestionDAO;
import com.ankan.educational.dao.SectionDAO;
import com.ankan.educational.dao.TopicDAO;
import com.ankan.educational.model.Material;
import com.ankan.educational.model.User;

@Controller
public class AdminMaterialController {
	@Autowired
	public CourseDAO coursedao;

	@Autowired
	public EnrollmentDAO enrollmentdao;

	@Autowired
	public TopicDAO topicdao;

	@Autowired
	public SectionDAO sectiondao;
	
	@Autowired
	public QuestionDAO questiondao;
	
	@Autowired
	public MaterialDAO materialdao;

	public ModelAndView addAttributes(int courseid,int sectionid, int topicid) {

		ModelAndView model = new ModelAndView("MaterialCreation");
		model.addObject("materials", materialdao.list(topicid));
		model.addObject("sectionid", sectionid);
		model.addObject("courseid",courseid);
		model.addObject("topicid",topicid);
		model.addObject("courseName", coursedao.get(courseid).getName());
		model.addObject("sectionName", sectiondao.get(sectionid, courseid).getName());
		model.addObject("topicName",topicdao.get(topicid).getName());
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/videomaterial", method = RequestMethod.GET)
	public ModelAndView videoCreation(@PathVariable("sectionid") int sectionid,
			@PathVariable("courseid") int courseid,@PathVariable("topicid") int topicid,HttpSession session) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = addAttributes(courseid,sectionid,topicid);
		model.addObject("material","Video Links");
		model.addObject("videolinks",materialdao.allVideoLinks(topicid));
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/videomaterial/materialsave", method = RequestMethod.POST)
	public ModelAndView videoSuccess(@RequestParam Map<String, String> allResponses,@PathVariable("courseid") int courseid, @PathVariable("sectionid") int sectionid,@PathVariable("topicid") int topicid,HttpSession session) {
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		Material material = new Material();
		material.setName("Video Links");
		material.setTopicid(topicid);
		String link = allResponses.get("link");
		materialdao.saveOrUpdate(material);
		int id = materialdao.getID();
		materialdao.insertInMaterialLinks(id,link);
		ModelAndView model = new ModelAndView("redirect:/admin/courses/" + courseid + "/" + sectionid +"/"+topicid+"/"+"videomaterial");
		return model;
	}
	
	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/videomaterial/materialdelete/{link}")
	public ModelAndView videoDelete(@PathVariable("courseid") int courseid, @PathVariable("sectionid") int sectionid,@PathVariable("topicid") int topicid,@PathVariable("link") String link,HttpSession session)
	{
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		materialdao.deleteMaterial(link);
		ModelAndView model = addAttributes(courseid,sectionid,topicid);
		model.addObject("material","Video Links");
		model.addObject("videolinks",materialdao.allVideoLinks(topicid));
		return model;
	}

}
