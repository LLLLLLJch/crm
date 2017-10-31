package com.menglang.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.SaleChance;
import com.menglang.crm.service.ISaleChanceService;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {

	@Autowired
	private ISaleChanceService saleChanceService;

	@RequestMapping("/index")
	public String saleChanceIndex() {
		return "sale_chance_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, SaleChance saleChance) {
		return saleChanceService.findAll(page, rows, saleChance);
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
}
