package com.menglang.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.pojo.CustomerLinkman;
import com.menglang.crm.service.ICustomerLinkmanService;

@Controller
@RequestMapping("/customerLinkman")
public class CustomerLinkManController {

	@Autowired
	private ICustomerLinkmanService customerLinkmanService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String index() {
		return "customer_linkman_index";
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLinkman customerLinkman) {
		System.out.println(customerLinkman);
		return customerLinkmanService.findAll(page, rows, customerLinkman);
	}
	@RequestMapping("/add")
	@ResponseBody
	public int add(CustomerLinkman customerLinkman) {
		return customerLinkmanService.add(customerLinkman);
	}
	@RequestMapping("/update")
	@ResponseBody
	public int update(CustomerLinkman customerLinkman) {
		return customerLinkmanService.update(customerLinkman);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(Integer id) {
		return customerLinkmanService.delete(id);
	}
}
