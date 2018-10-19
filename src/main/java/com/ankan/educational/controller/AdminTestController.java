package com.ankan.educational.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.MaterialDAO;
import com.ankan.educational.dao.TestDAO;
import com.ankan.educational.dao.TopicDAO;
import com.ankan.educational.model.Material;
import com.ankan.educational.model.Test;
import com.ankan.educational.model.Topic;
import com.ankan.educational.model.User;

@Controller
public class AdminTestController {

	@Autowired
	TopicDAO topicdao;

	@Autowired
	MaterialDAO materialdao;

	@Autowired
	TestDAO testdao;

	@RequestMapping(value = "/admin/tests", method = RequestMethod.GET)
	public ModelAndView createTests(HttpSession session) {
		if (!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = new ModelAndView("TestCreation");
		List<Topic> topics = topicdao.allTopics();
		if (!topics.isEmpty())
			model.addObject("topics", topics);
		model.addObject("tests", testdao.list());
		return model;
	}

	@RequestMapping(value = "/admin/tests", method = RequestMethod.POST)
	public ModelAndView saveTests(@ModelAttribute("update") String update,@RequestParam("id") int testid,
			@RequestParam("topicid") int topicid,@RequestParam("description") String description,@RequestParam("duration") int duration,@RequestParam("maxScore") int maxScore,HttpSession session) {

		if (!User.isValid(session))
			return new ModelAndView("redirect:/login");

		ModelAndView model = new ModelAndView("TestCreation");

		List<Topic> topics = topicdao.allTopics();

		model.addObject("topics", topics);
		
		Test test = new Test();
		
		test.setDescription(description);
		
		test.setDuration(duration);
		
		test.setMaxScore(maxScore);
		
		test.setId(testid);

		System.out.println(update+ "ANKAN");
		
		System.out.println(update!=null);
		if (testid!=-1) {
			
			testdao.saveOrUpdate(test);
			model.addObject("tests", testdao.list());
			return model;
		}
		
		System.out.println("HI ANKAN");
		
		testdao.saveOrUpdate(test);
		
		Material material = new Material();
		
		material.setName("Tests");
		
		System.out.println(topicid);
		
		material.setTopicid(topicid);
		
		materialdao.saveOrUpdate(material);
		int id = materialdao.getID();
		String link = String.valueOf(testdao.getID());
		materialdao.insertInMaterialLinks(id, link);
		model.addObject("tests", testdao.list());
		return model;
	}

	@RequestMapping(value = "/admin/tests/delete/{testid}")
	public ModelAndView deleteTests(HttpSession session,@PathVariable("testid") int testid) {


		if (!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		String link = String.valueOf(testid);
		
		System.out.println(materialdao.getMaterialFromLinks(link));
		
		int materialid = materialdao.getMaterialFromLinks(link);
		
		materialdao.deleteMaterial(link);
		
		materialdao.delete(materialid);
		
		testdao.delete(testid);
		
		ModelAndView model = new ModelAndView("TestCreation");

		List<Topic> topics = topicdao.allTopics();

		model.addObject("topics", topics);
		
		model.addObject("tests", testdao.list());
		
		return model;
	}
	
	@RequestMapping(value = "/admin/tests/update/{testid}",method = RequestMethod.GET)
	public ModelAndView updateTests(HttpSession session,@PathVariable("testid") int testid) {


		if (!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		ModelAndView model = new ModelAndView("TestCreation");

		Test newtest = new Test();
		
		Test oldtest = testdao.get(testid);
		
		List<Topic> topics = topicdao.allTopics();

		model.addObject("topics", topics);
		
		model.addObject("tests", testdao.list());
		
		newtest.setDescription(oldtest.getDescription());
		
		newtest.setDuration(oldtest.getDuration());
		
		newtest.setId(testid);
		
		newtest.setMaxScore(oldtest.getMaxScore());
		
		model.addObject("test",newtest);
		
		model.addObject("update","update");
		
		return model;
	}

}
