package com.menglang.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerContactMapper;
import com.menglang.crm.pojo.CustomerContact;
import com.menglang.crm.pojo.CustomerContactExample;
import com.menglang.crm.pojo.CustomerContactExample.Criteria;
import com.menglang.crm.service.ICustomerContactService;
@Service
public class CustomerContactServiceImpl implements ICustomerContactService{

	@Autowired
	private CustomerContactMapper customerContactMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerContact customerContact) {
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerContactExample customerContactExample = new CustomerContactExample();
		Criteria criteria = customerContactExample.createCriteria();
		if(customerContact != null){
			criteria.andCustomerIdEqualTo(customerContact.getCustomerId());
		}
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		
		List<CustomerContact> customerContactList = customerContactMapper.selectByExample(customerContactExample);
		//total
		PageInfo<CustomerContact> pageInfo = new PageInfo<CustomerContact>(customerContactList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerContactList);
		return result;
	}

	@Override
	public int add(CustomerContact customerContact) {
		return customerContactMapper.insert(customerContact);
	}

	@Override
	public int update(CustomerContact customerContact) {
		return customerContactMapper.updateByPrimaryKey(customerContact);
	}

	@Override
	public int delete(Integer id) {
		return customerContactMapper.deleteByPrimaryKey(id);
	}


}
