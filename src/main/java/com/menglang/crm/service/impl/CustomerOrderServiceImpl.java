package com.menglang.crm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.mapper.CustomerOrderMapper;
import com.menglang.crm.pojo.CustomerOrder;
import com.menglang.crm.pojo.CustomerOrderExample;
import com.menglang.crm.pojo.CustomerOrderExample.Criteria;
import com.menglang.crm.service.ICustomerOrderService;
@Service
public class CustomerOrderServiceImpl implements ICustomerOrderService{

	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerOrder customerOrder) {
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerOrderExample customerOrderExample = new CustomerOrderExample();
		Criteria criteria = customerOrderExample.createCriteria();
		if(customerOrder != null){
			criteria.andCustomerIdEqualTo(customerOrder.getCustomerId());
		}
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		
		List<CustomerOrder> customerOrderList = customerOrderMapper.selectByExample(customerOrderExample);
		//total
		PageInfo<CustomerOrder> pageInfo = new PageInfo<CustomerOrder>(customerOrderList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerOrderList);
		return result;
	}

	@Override
	public int add(CustomerOrder customerOrder) {
		return customerOrderMapper.insert(customerOrder);
	}

	@Override
	public int update(CustomerOrder customerOrder) {
		return customerOrderMapper.updateByPrimaryKey(customerOrder);
	}

	@Override
	public int delete(Integer id) {
		return customerOrderMapper.deleteByPrimaryKey(id);
	}


}
