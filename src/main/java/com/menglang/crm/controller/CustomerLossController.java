package com.menglang.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerLoss;
import com.menglang.crm.service.ICustomerLossService;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController {

	@Autowired
	private ICustomerLossService customerLossService;

	@RequestMapping("/index")
	public String customerLossIndex() {
		return "customer_loss_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLoss customerLoss) {
		System.out.println(customerLoss);
		return customerLossService.findAll(page, rows, customerLoss);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(CustomerLoss customerLoss) {
		return customerLossService.addCustomerLoss(customerLoss);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return customerLossService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(CustomerLoss customerLoss) {
		return customerLossService.update(customerLoss);
	}
	@RequestMapping("/findById")
	@ResponseBody
	public CustomerLoss findById(Integer id) {
		return customerLossService.findById(id);
	}
	
	@RequestMapping("/confirmLoss")
	@ResponseBody
	public SeverResponse confirmLoss(CustomerLoss customerLoss) {
		System.out.println(customerLoss);
		return customerLossService.confirmLoss(customerLoss);
	}
}
