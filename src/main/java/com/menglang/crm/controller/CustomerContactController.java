package com.menglang.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.pojo.CustomerContact;
import com.menglang.crm.service.ICustomerContactService;

@Controller
@RequestMapping("/customerContact")
public class CustomerContactController {

	@Autowired
	private ICustomerContactService customerContactService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String index() {
		return "customer_contact_index";
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerContact customerContact) {
		System.out.println(customerContact.getCustomerId());
		return customerContactService.findAll(page, rows, customerContact);
	}
	@RequestMapping("/add")
	@ResponseBody
	public int add(CustomerContact customerContact) {
		return customerContactService.add(customerContact);
	}
	@RequestMapping("/update")
	@ResponseBody
	public int update(CustomerContact customerContact) {
		return customerContactService.update(customerContact);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(Integer id) {
		return customerContactService.delete(id);
	}
}
