package com.ankan.educational.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ankan.educational.dao.UserDAO;
import com.ankan.educational.model.User;

@Controller
public class LoginController {

	@Autowired
	UserDAO userdao;
	
	@RequestMapping(value= "/login",method=RequestMethod.GET)
	public ModelAndView login(HttpSession session) {   
		
		if(!User.isValid(session))
			return new ModelAndView("login");	
		String role = (String)session.getAttribute("username");
		if(role.compareTo("ROLE_ADMIN")==0)
			return new ModelAndView("redirect:/admin");
		return new ModelAndView("redirect:/dashboard");
    }
	
	@RequestMapping(value= "/logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) { 
		session.setAttribute("username",null);
		session.setAttribute("password",null);
		ModelAndView model = new ModelAndView("login");
		model.addObject("msg","You've been successfully logged out");
        return model;
    }
	
	@RequestMapping(value= "/login",method=RequestMethod.POST)
	public ModelAndView login2(HttpSession session,@RequestParam("username") String username,@RequestParam("password") String password) {
		
		
		if(userdao.get(username, password)!=null) {
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			System.out.println(username);
			System.out.println(password);
			
			String role = userdao.getRole(username,password);
			
			System.out.println(role);
			String admin = "ROLE_ADMIN";
			String student = "ROLE_USER";
			if(role.compareTo(admin) == 0)
				return new ModelAndView("redirect:/admin");
			else if(role.compareTo(student)==0)
			{
				System.out.println("HI ANKAN");
				ModelAndView model = new ModelAndView("redirect:/dashboard","username",username);
				return model;
			}
			return new ModelAndView("redirect:/login"); 
		}
		session.setAttribute("username",null);
		session.setAttribute("password",null);
		return new ModelAndView("login", "error", "Invalid Credentials!");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(HttpSession session){
		
		session.setAttribute("username",null);
		session.setAttribute("password",null);
		ModelAndView model = new ModelAndView("register");
		model.addObject("user",new User());
		return model;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register2(HttpSession session,@ModelAttribute("user") User user,BindingResult result) {
		
		if(result.hasErrors())
		{
			return new ModelAndView("register");
		}

		if(userdao.getByUsername(user.getUsername())!=null)
		{
			ModelAndView model = new ModelAndView("register");
			model.addObject("error","Username already exists.Try with different username");
			model.addObject("user",new User());
			return model;
		}
		System.out.println(user.getEmail());
		System.out.println(user.getAddress());
		System.out.println(user.getUsername());
		userdao.saveOrUpdate(user);
		session.setAttribute("username",user.getUsername());
		session.setAttribute("password",user.getPassword());
		ModelAndView model = new ModelAndView("redirect:/dashboard");
		return model;
	}
}
