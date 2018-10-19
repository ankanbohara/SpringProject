package com.ankan.educational.controller;

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
import com.ankan.educational.dao.QuestionDAO;
import com.ankan.educational.dao.SectionDAO;
import com.ankan.educational.dao.TopicDAO;
import com.ankan.educational.model.Question;
import com.ankan.educational.model.User;

@Controller
public class AdminQuestionController {

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

	public ModelAndView addAttributes(int courseid,int sectionid, int topicid) {

		ModelAndView model = new ModelAndView("QuestionCreation");
		model.addObject("question", new Question());
		model.addObject("questions", questiondao.list(topicid));
		model.addObject("sectionid", sectionid);
		model.addObject("courseid",courseid);
		model.addObject("topicid",topicid);
		model.addObject("courseName", coursedao.get(courseid).getName());
		model.addObject("sectionName", sectiondao.get(sectionid, courseid).getName());
		model.addObject("topicName",topicdao.get(topicid).getName());
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}", method = RequestMethod.GET)
	public ModelAndView sectionCreation(@PathVariable("sectionid") int sectionid,
			@PathVariable("courseid") int courseid,@PathVariable("topicid") int topicid,HttpSession session) {
		
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = addAttributes(courseid,sectionid,topicid);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/questionsave", method = RequestMethod.POST)
	public ModelAndView topicSuccess(@PathVariable("courseid") int courseid, @PathVariable("sectionid") int sectionid,@PathVariable("topicid") int topicid,
			@ModelAttribute("question") Question question, BindingResult result,HttpSession session) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		if (result.hasErrors()) {

			return addAttributes(courseid,sectionid,topicid);
		}
		ModelAndView model = new ModelAndView("redirect:/admin/courses/" + courseid + "/" + sectionid +"/"+topicid);
		questiondao.saveOrUpdate(question);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/questionupdate/{questionid}",method=RequestMethod.GET)
	public ModelAndView sectionUpdate(@ModelAttribute("question") Question question, @PathVariable("courseid") int courseid,
			@PathVariable("sectionid") int sectionid,@PathVariable("topicid") int topicid,@PathVariable("questionid") int questionid,HttpSession session) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = new ModelAndView("QuestionCreation");
		Question q = new Question();
		Question find = questiondao.get(questionid);
		q.setCorrectans(find.getCorrectans());
		q.setDescription(find.getDescription());
		q.setId(find.getId());
		q.setOption1(find.getOption1());
		q.setOption2(find.getOption2());
		q.setOption3(find.getOption3());
		q.setOption4(find.getOption4());
		q.setTopicid(find.getTopicid());
		model.addObject("question",q);
		model.addObject("questions", questiondao.list(topicid));
		model.addObject("sectionid", sectionid);
		model.addObject("courseid",courseid);
		model.addObject("topicid",topicid);
		model.addObject("courseName", coursedao.get(courseid).getName());
		model.addObject("sectionName", sectiondao.get(sectionid, courseid).getName());
		model.addObject("topicname",topicdao.get(topicid).getName());
		model.addObject("update", "update");
//		model.addObject("qid",questionid);
		return model;
	}

	@RequestMapping(value = "/admin/courses/{courseid}/{sectionid}/{topicid}/questiondelete/{questionid}")
	public ModelAndView topicDelete(@PathVariable("topicid") int topicid, @PathVariable("courseid") int courseid,
			@PathVariable("sectionid") int sectionid,HttpSession session) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		questiondao.delete(topicid);
		ModelAndView model = addAttributes(courseid,sectionid,topicid);
		return model;
	}

}
