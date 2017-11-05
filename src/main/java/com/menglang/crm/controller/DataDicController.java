package com.menglang.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.DataDic;
import com.menglang.crm.service.IDataDicService;

@Controller
@RequestMapping("/dataDic")
public class DataDicController {

	@Autowired
	private IDataDicService dataDicService;

	@RequestMapping("/index")
	public String dataDicIndex() {
		return "data_dic_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, DataDic dataDic) {
		return dataDicService.findAll(page, rows, dataDic);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(DataDic dataDic) {
		return dataDicService.addDataDic(dataDic);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return dataDicService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(DataDic dataDic) {
		return dataDicService.update(dataDic);
	}
	@RequestMapping("/findDataDicName")
	@ResponseBody
	public List<DataDic> findDataDicName() {
		return dataDicService.findDataDicName();
	}
	@RequestMapping("/findLevel")
	@ResponseBody
	public List<DataDic> findLevel() {
		return dataDicService.findLevel();
	}
	@RequestMapping("/findServiceType")
	@ResponseBody
	public List<DataDic> findServiceType() {
		List<DataDic> list = dataDicService.findServiceType();
		System.out.println(list);
		return list;
	}
	
}
