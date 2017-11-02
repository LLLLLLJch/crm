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
import com.menglang.crm.pojo.CusDevPlan;
import com.menglang.crm.service.ICusDevPlanService;

@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {

	@Autowired
	private ICusDevPlanService cusDevPlanService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/index")
	public String index(Integer saleChanceId,Model model) {
		model.addAttribute("saleChanceId", saleChanceId);
		return "cus_dev_plan";
	}
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CusDevPlan cusDevPlan) {
		return cusDevPlanService.findAll(page, rows, cusDevPlan);
	}
	@RequestMapping("/add")
	@ResponseBody
	public int add(CusDevPlan cusDevPlan) {
		System.out.println(cusDevPlan);
		return cusDevPlanService.add(cusDevPlan);
	}
	@RequestMapping("/update")
	@ResponseBody
	public int update(CusDevPlan cusDevPlan) {
		return cusDevPlanService.update(cusDevPlan);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(Integer id) {
		return cusDevPlanService.delete(id);
	}
}
