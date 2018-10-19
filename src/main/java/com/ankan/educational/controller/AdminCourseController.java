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
import com.ankan.educational.model.Course;
import com.ankan.educational.model.Section;
import com.ankan.educational.model.User;

@Controller
public class AdminCourseController {
	
	
	@Autowired
	public CourseDAO coursedao;
	
	@Autowired
	public EnrollmentDAO enrollmentdao;
	
	@Autowired
	public SectionDAO sectiondao;
	
	@RequestMapping(value="/admin/courses",method=RequestMethod.GET)
	public ModelAndView courseCreation(HttpSession session)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		ModelAndView model = new ModelAndView("CourseCreation");
		model.addObject("course",new Course());
		List<Course>courses = coursedao.allCourses();
		model.addObject("courses",courses);
		return model;
	}
	
	@RequestMapping(value="/admin/coursesave",method=RequestMethod.POST)
	public ModelAndView courseSuccess(@ModelAttribute("update") String update,@ModelAttribute("course") Course course,BindingResult result,HttpSession session)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		if(result.hasErrors())
		{
			return new ModelAndView("CourseCreation");
		}
		if(update!=null)
		{
			coursedao.saveOrUpdate(course);
			return new ModelAndView("redirect:/admin/courses");
		}
		if(coursedao.get(course.getId())!=null)
		{
			
			ModelAndView model = new ModelAndView("CourseCreation");
			model.addObject("courses",coursedao.allCourses());
			model.addObject("error","Can't be added as CourseID already exists");
			return model;
		}
		coursedao.saveOrUpdate(course);
		return new ModelAndView("redirect:/admin/courses");
	}

	@RequestMapping(value="/admin/coursedelete/{courseid}")
	public ModelAndView courseDelete(@PathVariable("courseid") int id,HttpSession session)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		
		
		ModelAndView model = new ModelAndView("CourseCreation");
		List<Section>ses = sectiondao.list(id);
		for(Section t:ses)
		{
			System.out.println(t.getCourseid());
//			System.out.println(t.getName());
//			System.out.println(t.getDescription());
		}
		List<Integer>allenrolledCourses = enrollmentdao.getallCoursesEnrollesbyAny();
		List<Integer>courseContainingSections = coursedao.containSection();
		if(allenrolledCourses.contains(id) || courseContainingSections.contains(id))	
		{
			model.addObject("error","Can't be deleted as it is enrolled by some students or it has few sections. So delete sections first in case enrolled students in zero");
			model.addObject("course",new Course());
			model.addObject("courses",coursedao.allCourses());
		}
		else
		{
			coursedao.delete(id);
			model.addObject("course",new Course());
			model.addObject("courses",coursedao.allCourses());
		}
		return model;
	}
	
	@RequestMapping(value="/admin/courseupdate/{courseid}")
	public ModelAndView courseUpdate(@PathVariable("courseid") int cid,HttpSession session)
	{
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");

		ModelAndView model = new ModelAndView("CourseCreation");
		model.addObject("course",new Course());
		model.addObject("update","update");
		model.addObject("courses",coursedao.allCourses());
		return model;
	}
	
}
