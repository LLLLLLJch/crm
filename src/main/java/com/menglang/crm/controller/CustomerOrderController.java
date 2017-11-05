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
import com.menglang.crm.pojo.CustomerOrder;
import com.menglang.crm.service.ICustomerOrderService;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController {

	@Autowired
	private ICustomerOrderService customerOrderService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String index() {
		return "customer_order_index";
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerOrder customerOrder) {
		System.out.println(customerOrder);
		return customerOrderService.findAll(page, rows, customerOrder);
	}
	@RequestMapping("/add")
	@ResponseBody
	public int add(CustomerOrder customerOrder) {
		return customerOrderService.add(customerOrder);
	}
	@RequestMapping("/update")
	@ResponseBody
	public int update(CustomerOrder customerOrder) {
		return customerOrderService.update(customerOrder);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(Integer id) {
		return customerOrderService.delete(id);
	}
}
