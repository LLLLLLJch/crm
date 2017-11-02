package com.menglang.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerMapper;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.pojo.CustomerExample;
import com.menglang.crm.pojo.CustomerExample.Criteria;
import com.menglang.crm.service.ICustomerService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,Customer customer) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(customer.getName())){
			criteria.andNameLike(LikeNameUtil.formartLike(customer.getName()));
		}
		if(StringUtils.isNotBlank(customer.getNum())){
			criteria.andNumLike(LikeNameUtil.formartLike(customer.getNum()));
		}
		//2、执行查询  
		List<Customer> list = customerMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addCustomer(Customer customer) {
		CustomerExample example = new CustomerExample();
		if(customerMapper.insert(customer)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public Customer findById(Integer id) {
		return customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(Customer customer) {
		if(customerMapper.updateByPrimaryKey(customer)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}
}
