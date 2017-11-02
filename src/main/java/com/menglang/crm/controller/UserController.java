package com.menglang.crm.controller;

import java.util.List;

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
	public EasyuiDataGridResult findAll(Integer page, Integer rows, User user) {
		return userService.findAll(page, rows, user);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(User user) {
		return userService.addUser(user);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return userService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(User user) {
		return userService.update(user);
	}
	@RequestMapping("/checkName")
	@ResponseBody
	public SeverResponse checkName(String name) {
		return userService.checkName(name);
	}
	@RequestMapping("/checkPasswordByName")
	@ResponseBody
	public SeverResponse checkPasswordByName(String name,String password) {
		return userService.checkPasswordByName(name,password);
	}
	@RequestMapping("/surePassword")
	@ResponseBody
	public SeverResponse surePassword(String newPassword,String surePassword) {
		return userService.surePassword(newPassword,surePassword);
	}
	@RequestMapping("/updatePassword")
	@ResponseBody
	public SeverResponse updatePassword(User user,String newPassword) {
		return userService.updatePassword(user,newPassword);
	}
	@RequestMapping("/findManger")
	@ResponseBody
	public List<User> findManger() {
		return userService.findManger();
	}
}
