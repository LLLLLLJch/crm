package com.menglang.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.pojo.User;
import com.menglang.crm.service.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/goLoginPage")
	public String goLoginPage() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,String name,String password,String roleName) {
		User user = userService.checkUser(name, password,roleName);
		if(user != null){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "redirect:/index/index.action";
		}
		return "fail";
	}
	@RequestMapping("/goexit")
	public String goexit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/login/goLoginPage.action";
		
	}
}
