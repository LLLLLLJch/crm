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
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerLossMeasure;
import com.menglang.crm.service.ICustomerLossMeasureService;

@Controller
@RequestMapping("/customerLossMeasure")
public class CustomerLossMeasureController {

	@Autowired
	private ICustomerLossMeasureService customerLossMeasureService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String index() {
		return "customer_loss_measure";
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure) {
		return customerLossMeasureService.findAll(page, rows, customerLossMeasure);
	}
	@RequestMapping("/add")
	@ResponseBody
	public int add(CustomerLossMeasure customerLossMeasure) {
		System.out.println(customerLossMeasure);
		return customerLossMeasureService.add(customerLossMeasure);
	}
	@RequestMapping("/update")
	@ResponseBody
	public int update(CustomerLossMeasure customerLossMeasure) {
		return customerLossMeasureService.update(customerLossMeasure);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(Integer id) {
		return customerLossMeasureService.delete(id);
	}
}
