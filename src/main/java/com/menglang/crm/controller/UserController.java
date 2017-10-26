package com.menglang.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.User;
import com.menglang.crm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/index")
	public String userIndex() {
		return "user_index";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page,Integer rows,User user) {
		return userService.findAll(page,rows,user);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse addUser(User user) {
		return userService.addUser(user);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return userService.delete(ids);
	}
	@RequestMapping("/toUpdate")
	@ResponseBody
	public User toUpdate(Integer id) {
		return userService.findById(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(User user) {
		return userService.update(user);
	}
}
