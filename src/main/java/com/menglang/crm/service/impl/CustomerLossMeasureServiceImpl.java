package com.menglang.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerLossMeasureMapper;
import com.menglang.crm.pojo.CustomerLossMeasure;
import com.menglang.crm.pojo.CustomerLossMeasureExample;
import com.menglang.crm.pojo.CustomerLossMeasureExample.Criteria;
import com.menglang.crm.service.ICustomerLossMeasureService;
@Service
public class CustomerLossMeasureServiceImpl implements ICustomerLossMeasureService{

	@Autowired
	private CustomerLossMeasureMapper customerLossMeasureMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure) {
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerLossMeasureExample customerLossMeasureExample = new CustomerLossMeasureExample();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Criteria criteria = customerLossMeasureExample.createCriteria();
		criteria.andLossIdEqualTo(customerLossMeasure.getLossId());
		
		List<CustomerLossMeasure> customerLossMeasureList = customerLossMeasureMapper.selectByExample(customerLossMeasureExample);
		//total
		PageInfo<CustomerLossMeasure> pageInfo = new PageInfo<CustomerLossMeasure>(customerLossMeasureList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerLossMeasureList);
		return result;
	}

	@Override
	public int add(CustomerLossMeasure customerLossMeasure) {
		return customerLossMeasureMapper.insert(customerLossMeasure);
	}

	@Override
	public int update(CustomerLossMeasure customerLossMeasure) {
		return customerLossMeasureMapper.updateByPrimaryKey(customerLossMeasure);
	}

	@Override
	public int delete(Integer id) {
		return customerLossMeasureMapper.deleteByPrimaryKey(id);
	}


}
