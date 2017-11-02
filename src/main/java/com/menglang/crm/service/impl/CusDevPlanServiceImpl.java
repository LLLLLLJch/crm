package com.menglang.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CusDevPlanMapper;
import com.menglang.crm.pojo.CusDevPlan;
import com.menglang.crm.pojo.CusDevPlanExample;
import com.menglang.crm.pojo.CusDevPlanExample.Criteria;
import com.menglang.crm.service.ICusDevPlanService;
@Service
public class CusDevPlanServiceImpl implements ICusDevPlanService{

	@Autowired
	private CusDevPlanMapper cusDevPlanMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CusDevPlan cusDevPlan) {
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CusDevPlanExample cusDevPlanExample = new CusDevPlanExample();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Criteria criteria = cusDevPlanExample.createCriteria();
		criteria.andSaleChanceIdEqualTo(cusDevPlan.getSaleChanceId());
		
		List<CusDevPlan> cusDevPlanList = cusDevPlanMapper.selectByExample(cusDevPlanExample);
		//total
		PageInfo<CusDevPlan> pageInfo = new PageInfo<CusDevPlan>(cusDevPlanList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(cusDevPlanList);
		return result;
	}

	@Override
	public int add(CusDevPlan cusDevPlan) {
		return cusDevPlanMapper.insert(cusDevPlan);
	}

	@Override
	public int update(CusDevPlan cusDevPlan) {
		return cusDevPlanMapper.updateByPrimaryKey(cusDevPlan);
	}

	@Override
	public int delete(Integer id) {
		return cusDevPlanMapper.deleteByPrimaryKey(id);
	}


}
