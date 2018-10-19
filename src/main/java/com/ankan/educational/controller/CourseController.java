package com.ankan.educational.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.CourseDAO;
import com.ankan.educational.dao.MaterialDAO;
import com.ankan.educational.dao.QuestionDAO;
import com.ankan.educational.dao.ScoreDAO;
import com.ankan.educational.dao.SectionDAO;
import com.ankan.educational.dao.TestDAO;
import com.ankan.educational.dao.TestQuestionsDAO;
import com.ankan.educational.dao.TopicDAO;
import com.ankan.educational.model.Course;
import com.ankan.educational.model.Material;
import com.ankan.educational.model.Question;
import com.ankan.educational.model.Score;
import com.ankan.educational.model.Section;
import com.ankan.educational.model.Test;
import com.ankan.educational.model.TestQuestions;
import com.ankan.educational.model.Topic;
import com.ankan.educational.model.User;

@Controller
public class CourseController {

	public List<Question> qset;

	@Autowired
	CourseDAO coursedao;

	@Autowired
	SectionDAO sectiondao;

	@Autowired
	TestDAO testdao;

	@Autowired
	QuestionDAO questiondao;

	@Autowired
	TestQuestionsDAO testquestionsdao;

	@Autowired
	TopicDAO topicdao;

	@Autowired
	ScoreDAO scoredao;

	@Autowired
	MaterialDAO materialdao;

	@RequestMapping("/{userid}/{courseid}")
	public String course(@PathVariable("userid") int uid, @PathVariable("courseid") int courseid, Model model,
			HttpSession session) {

		if (!User.isValid(session))
			return "redirect:/login";
		model.addAttribute("username", (String) session.getAttribute("username"));
		model.addAttribute("userid", uid);
		model.addAttribute("courseid", courseid);
		Course c = coursedao.get(courseid);
		model.addAttribute("course", c);
		List<Section> s = sectiondao.list(courseid);
		model.addAttribute("section", s);
		return "Course";

	}

	@RequestMapping("/{userid}/{courseid}/{sectionid}")
	public ModelAndView section(@PathVariable("userid") int uid, @PathVariable("courseid") int courseid,
			@PathVariable("sectionid") int sectionid, HttpSession session) {

		if (!User.isValid(session))
			return new ModelAndView("redirect:/login");

		ModelAndView model = new ModelAndView("Topic");

		model.addObject("username", (String) session.getAttribute("username"));

		List<Topic> topics = topicdao.list(sectionid, courseid);

//		model.addAttribute("topics",topics);

		model.addObject("userid", uid);

		Section s = sectiondao.get(sectionid, courseid);
		model.addObject("section", s);
//		model.addAttribute("topics",topics);
		
		Map <Topic, List< Material > > topicMap = new HashMap<Topic, List<Material>>();
		
		Map < Material, String > linksMap = new HashMap< Material,String >();
		
		List <String> tests = new ArrayList<String>();
		
		for(Topic t:topics)
		{
			List < Material > materials = materialdao.list(t.getId());
			
			topicMap.put(t,materials);
			
			for(Material m : materials)
			{
				
				System.out.println(m.getName());
				
				String se = materialdao.getUrlOfMaterial(m.getId());
				
				linksMap.put(m,se);
				
				if(m.getName().compareTo("Tests")==0)
				{
					tests.add(se);
					System.out.println("HI BOHARA JI");
				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		for (Topic t : topics) {
//
//			List < Material > materials = materialdao.list(t.getId());
//			topicMap.put(t, materials);
//			System.out.println(t.getName());
//			for (Material m : materials) {
//				System.out.println(m.getName());
//				System.out.println(m.getId());
//				System.out.println(m.getTopicid());
//				List<String> links = linksMap.get(m.getName());
//				if (links == null) {
//					links = new ArrayList<>();
//					linksMap.put(m.getName(), links);
//				}
//				links.addAll(materialdao.urls(m.getId()));
////				linksMap.put(m.getName(), materialdao.urls(m.getId()));
//			}
//		}
		System.out.println(topicMap.size());
		
		
		for (String i : tests) {
			System.out.println(i);
		}

		Map < String, List<Score> > scoreMap = new HashMap<String, List<Score>>();
		if (tests != null) {
			for (String ids : tests) {

				int findID = Integer.parseInt(ids);
				scoreMap.put(ids, scoredao.list(findID, uid));
			}

		}
		model.addObject("scoreMap", scoreMap);

//		if(testM==null)
//		{
//			return new ModelAndView("redirect:/"+uid+"/"+courseid,"error","The section has no topics. It will be added soon");
//		}

		model.addObject("topicMap", topicMap);
		model.addObject("linkMap", linksMap);
		return model;

	}

	@RequestMapping(value = "/{userid}/{courseid}/{sectionid}/{topicid}/{testid}", method = RequestMethod.GET)
	public String quiz(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid,
			@PathVariable("topicid") int topicid, @PathVariable int testid, @PathVariable("sectionid") int sectionid,
			Model model) {

		List<Question> qtopic = questiondao.list(topicid);

		if (qtopic.size() < 10) {
			String link = "redirect:/" + userid + "/" + courseid + "/" + sectionid;
			return link;
		}

		Collections.shuffle(qtopic);

		qset = qtopic.subList(0, 10);

		model.addAttribute("listquestions", qset);
//
//		for (Question q : qset) {
//			System.out.println(q.getOption1());
//		}

		for (Question q : qset) {
			TestQuestions tq = new TestQuestions();
			tq.setQuestionid(q.getId());
			tq.setTestid(testid);
			tq.setUserid(userid);
			testquestionsdao.saveOrUpdate(tq);
		}

		Test t = testdao.get(testid);

		model.addAttribute("test", t);

//		System.out.println(t.getDescription());
		model.addAttribute("userid", userid);
		model.addAttribute("courseid", courseid);
		model.addAttribute("topicid", topicid);
		model.addAttribute("testid", testid);
		return "Quiz";
	}

	@RequestMapping(value = "/{userid}/{courseid}/{topicid}/{testid}/result", method = RequestMethod.POST)
	public String result(@PathVariable("userid") int userid, @PathVariable("courseid") int courseid,
			@PathVariable("topicid") int topicid, @PathVariable int testid,
			@RequestParam Map<String, String> allResponses, Model model) {
		int score = 0;
		int correct = 0;
		int notattempted = 0;
		for (int i = 0; i < qset.size(); i++) {
			String response = allResponses.get("option[" + i + "]");
			if (response != null) {

				int r = Integer.parseInt(response);
				if (r == qset.get(i).getCorrectans()) {
					correct++;
					score++;
				} else
					score--;
			} else {
				notattempted++;
			}
		}
		model.addAttribute("score", score);
		model.addAttribute("correct", correct);
		model.addAttribute("userid", userid);
		model.addAttribute("courseid", courseid);
		model.addAttribute("topicid", topicid);
		Score sq = new Score();
		sq.setCorrect(correct);
		sq.setScore(score);
		sq.setTestid(testid);
		sq.setUserid(userid);
		sq.setMaxScore(qset.size());
		sq.setNotattempted(notattempted);
		scoredao.saveOrUpdate(sq);
		return "Result";
	}
}
