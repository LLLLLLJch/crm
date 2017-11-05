package com.menglang.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.service.ICustomerService;
import com.menglang.crm.vo.CustomerComposition;
import com.menglang.crm.vo.CustomerContribution;
import com.menglang.crm.vo.CustomerServiceAnalysis;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/index")
	public String customerIndex() {
		return "customer_index";
	}
	@RequestMapping("/goCustomerContributionPage")
	public String goCustomerContributionPage() {
		return "customer_contribution";
	}
	@RequestMapping("/goCustomerCompositionPage")
	public String goCustomerCompositionPage() {
		return "customer_composition";
	}
	@RequestMapping("/goCustomerServiceAnalysisPage")
	public String goCustomerServiceAnalysisPage() {
		return "customer_service_analysis";
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
	@RequestMapping("/findById")
	@ResponseBody
	public Customer findById(Integer id) {
		return customerService.findById(id);
	}
	@RequestMapping("/findCustomerContribution")
	@ResponseBody
	public EasyuiDataGridResult findCustomerContribution(Integer page,Integer rows,CustomerContribution contribution) {
		return customerService.findCustomerContribution(page,rows,contribution);
		
	}
	@RequestMapping("/findCustomerComposition")
	@ResponseBody
	public SeverResponse<List<CustomerComposition>> findCustomerComposition() {
		return customerService.findCustomerComposition();
	}
	@RequestMapping("/customerServiceAnalysis")
	@ResponseBody
	public SeverResponse<List<CustomerServiceAnalysis>> customerServiceAnalysis() {
		return customerService.customerServiceAnalysis();
	}
}
