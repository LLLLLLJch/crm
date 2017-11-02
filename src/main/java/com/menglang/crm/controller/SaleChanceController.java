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
import com.menglang.crm.pojo.CusDevPlan;
import com.menglang.crm.pojo.SaleChance;
import com.menglang.crm.service.ISaleChanceService;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {

	@Autowired
	private ISaleChanceService saleChanceService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/index")
	public String saleChanceIndex() {
		return "sale_chance_index";
	}

	@RequestMapping("/salePlan")
	public String saleChancePlan() {
		return "sale_chance_plan";
	}
	@RequestMapping("/findStatusIsOne")
	@ResponseBody
	public EasyuiDataGridResult findStatusIsOne(Integer page,Integer rows) {
		return saleChanceService.findStatusIsOne(page,rows);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, SaleChance saleChance, String startDate,
			String endDate) {
		return saleChanceService.findAll(page, rows, saleChance, startDate, endDate);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(SaleChance saleChance) {
		return saleChanceService.addSaleChance(saleChance);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return saleChanceService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(SaleChance saleChance) {
		return saleChanceService.update(saleChance);
	}

	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<SaleChance> findAssignMan() {
		return saleChanceService.findAssignMan();
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public SaleChance findById(Integer id) {
		return saleChanceService.findById(id);
	}
	@RequestMapping("/stopDevelopment")
	@ResponseBody
	public SeverResponse stopDevelopment(Integer id) {
		return saleChanceService.stopDevelopment(id);
		
	}
}
