package com.menglang.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerService;
import com.menglang.crm.service.ICustomerServiceService;

@Controller
@RequestMapping("/customerService")
public class CustomerServiceController {

	@Autowired
	private ICustomerServiceService customerServiceService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/index")
	public String index() {
		return "customer_service_create";
	}
	@RequestMapping("/goServiceArchive")
	public String goServiceArchive() {
		return "service_archive";
	}
	
	@RequestMapping("/allocation")
	public String allocation() {
		return "service_allocation";
	}
	
	@RequestMapping("/process")
	public String process() {
		return "service_process";
	}
	@RequestMapping("/feedback")
	public String feedback() {
		return "service_feedback";
	}

	@RequestMapping("/salePlan")
	public String customerServicePlan() {
		return "sale_chance_plan";
	}
	@RequestMapping("/findStatusIsOne")
	@ResponseBody
	public EasyuiDataGridResult findStatusIsOne(Integer page,Integer rows) {
		return customerServiceService.findStatusIsOne(page,rows);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerService customerService, String startDate,
			String endDate) {
		return customerServiceService.findAll(page, rows, customerService, startDate, endDate);
	}
	@RequestMapping("/findArchive")
	@ResponseBody
	public EasyuiDataGridResult findArchive(Integer page, Integer rows, CustomerService customerService, String startDate,
			String endDate) {
		return customerServiceService.findArchive(page, rows, customerService, startDate, endDate);
	}
	
	@RequestMapping("/findProcessed")
	@ResponseBody
	public EasyuiDataGridResult findProcessed(Integer page, Integer rows, CustomerService customerService, String startDate,
			String endDate) {
		return customerServiceService.findProcessed(page, rows, customerService, startDate, endDate);
	}
	
	@RequestMapping("/findNotDel")
	@ResponseBody
	public EasyuiDataGridResult findNotDel(Integer page, Integer rows, CustomerService customerService) {
		return customerServiceService.findNotDel(page, rows, customerService);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(CustomerService customerService) {
		return customerServiceService.add(customerService);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return customerServiceService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(CustomerService customerService) {
		return customerServiceService.update(customerService);
	}
	@RequestMapping("/serviceProcess")
	@ResponseBody
	public SeverResponse process(CustomerService customerService) {
		return customerServiceService.process(customerService);
	}
	@RequestMapping("/serviceFeedback")
	@ResponseBody
	public SeverResponse serviceFeedback(CustomerService customerService) {
		return customerServiceService.serviceFeedback(customerService);
	}

	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<CustomerService> findAssignMan() {
		return customerServiceService.findAssignMan();
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public CustomerService findById(Integer id) {
		return customerServiceService.findById(id);
	}
	@RequestMapping("/stopDevelopment")
	@ResponseBody
	public SeverResponse stopDevelopment(Integer id) {
		return customerServiceService.stopDevelopment(id);
		
	}
	@RequestMapping("/findServiceDetail")
	@ResponseBody
	public CustomerService findServiceDetail(Integer id) {
		return customerServiceService.findServiceDetail(id);
	}
}
