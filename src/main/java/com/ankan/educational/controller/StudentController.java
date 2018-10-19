package com.ankan.educational.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.CourseDAO;
import com.ankan.educational.dao.EnrollmentDAO;
import com.ankan.educational.dao.FeesDAO;
import com.ankan.educational.dao.UserDAO;
import com.ankan.educational.model.Course;
import com.ankan.educational.model.Enrollment;
import com.ankan.educational.model.Fees;
import com.ankan.educational.model.User;

@Controller
public class StudentController {

	@Autowired
	public CourseDAO coursedao;

	@Autowired
	public EnrollmentDAO enrollmentdao;

	@Autowired
	public FeesDAO feesdao;

	@Autowired
	public UserDAO userdao;

//	@Autowired
//	public ScoreDAO scoredao;
//	

	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if (!User.isValid(session))
			return "redirect:/login";
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		System.out.println(username);
		int uid = userdao.get(username, password).getUserid();
		List<Course> availableCourses = coursedao.list(uid);

		List<Integer> cids = enrollmentdao.listCourses(uid);
		List<Course> enrolledCourses = new ArrayList<Course>();
		for (Integer id : cids) {
			System.out.println(id);
			Course c1 = coursedao.get(id);
			System.out.println(c1.getName());
			enrolledCourses.add(c1);
		}
		System.out.println(enrolledCourses.size());

		model.addAttribute("listCourse", availableCourses);
		model.addAttribute("listEnrolled", enrolledCourses);
		model.addAttribute("course", enrolledCourses != null);
		model.addAttribute("username", username);
		model.addAttribute("userid", uid);

		return "StudentDashboard";
	}

	@RequestMapping("/enrollment/{userid}/{courseid}/")
	public ModelAndView enrollment(HttpSession session, @PathVariable("userid") int uid,
			@PathVariable("courseid") int cid) {
		if(!User.isValid(session))
			return new ModelAndView("redirect:/login");
		int id = enrollmentdao.getID();
		Fees fee = new Fees();
		fee.setInvoice("IN" + id);
		fee.setAmount(coursedao.get(cid).getPrice());
		Random randomno = new Random();
		// get next long value
		long value = randomno.nextLong();
		fee.setTid(value);
		fee.setUid(uid);
		feesdao.saveOrUpdate(fee);
		Enrollment en = new Enrollment();
		en.setCourseid(cid);
		en.setInvoice(fee.getInvoice());
		en.setUserid(uid);
		enrollmentdao.saveOrUpdate(en);
		Course c = coursedao.get(cid);
		c.setNoOfEnrolled(c.getNoOfEnrolled()+1);
		coursedao.saveOrUpdate(c);
		return new ModelAndView("redirect:/dashboard");
	}
}
