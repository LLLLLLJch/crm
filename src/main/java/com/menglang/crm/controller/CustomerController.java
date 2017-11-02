package com.menglang.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/index")
	public String customerIndex() {
		return "customer_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, Customer customer) {
		System.out.println(customer);
		return customerService.findAll(page, rows, customer);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(Customer customer) {
		return customerService.addCustomer(customer);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return customerService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(Customer customer) {
		return customerService.update(customer);
	}
}
