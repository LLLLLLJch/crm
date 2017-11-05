package com.menglang.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerLinkmanMapper;
import com.menglang.crm.pojo.CustomerLinkman;
import com.menglang.crm.pojo.CustomerLinkmanExample;
import com.menglang.crm.pojo.CustomerLinkmanExample.Criteria;
import com.menglang.crm.service.ICustomerLinkmanService;
@Service
public class CustomerLinkmanServiceImpl implements ICustomerLinkmanService{

	@Autowired
	private CustomerLinkmanMapper customerLinkmanMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLinkman customerLinkman) {
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerLinkmanExample customerLinkmanExample = new CustomerLinkmanExample();
		Criteria criteria = customerLinkmanExample.createCriteria();
		if(customerLinkman != null){
			criteria.andCustomerIdEqualTo(customerLinkman.getCustomerId());
		}
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		
		List<CustomerLinkman> customerLinkmanList = customerLinkmanMapper.selectByExample(customerLinkmanExample);
		//total
		PageInfo<CustomerLinkman> pageInfo = new PageInfo<CustomerLinkman>(customerLinkmanList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerLinkmanList);
		return result;
	}

	@Override
	public int add(CustomerLinkman customerLinkman) {
		return customerLinkmanMapper.insert(customerLinkman);
	}

	@Override
	public int update(CustomerLinkman customerLinkman) {
		return customerLinkmanMapper.updateByPrimaryKey(customerLinkman);
	}

	@Override
	public int delete(Integer id) {
		return customerLinkmanMapper.deleteByPrimaryKey(id);
	}


}
