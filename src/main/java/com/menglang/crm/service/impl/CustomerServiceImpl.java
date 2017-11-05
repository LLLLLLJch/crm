package com.menglang.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerLossMapper;
import com.menglang.crm.mapper.CustomerMapper;
import com.menglang.crm.mapper.CustomerOrderMapper;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.pojo.CustomerExample;
import com.menglang.crm.pojo.CustomerLoss;
import com.menglang.crm.pojo.CustomerOrder;
import com.menglang.crm.pojo.CustomerExample.Criteria;
import com.menglang.crm.service.ICustomerService;
import com.menglang.crm.util.LikeNameUtil;
import com.menglang.crm.vo.CustomerComposition;
import com.menglang.crm.vo.CustomerContribution;
import com.menglang.crm.vo.CustomerServiceAnalysis;
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerLossMapper customerLossMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;

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

	@Override
	public void checkCustomerLoss() {
		List<Customer> customerlist = customerMapper.findLossCustomer();
		for (Customer customer : customerlist) {
			CustomerLoss customerLoss = new CustomerLoss();
			customerLoss.setCustomerNo(customer.getNum());
			customerLoss.setCustomerName(customer.getName());
			customerLoss.setCustomerManager(customer.getManagerName());
			CustomerOrder customerOrder = customerOrderMapper.findLastOrderByCustomerId(customer.getId());
			if(customerOrder == null){
				continue;
			}else {
				customerLoss.setLastOrderTime(customerOrder.getOrderDate());
			}
			customerLoss.setStatus(0);
			customerLossMapper.insert(customerLoss);
			customer.setStatus(1);
			customerMapper.updateByPrimaryKeySelective(customer);
			
		}
		
	}

	@Override
	public EasyuiDataGridResult findCustomerContribution(Integer page, Integer rows,
			CustomerContribution contribution) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		Map<String, Object> map = new HashMap<String,Object>();
		if(contribution != null){
			map.put("name", contribution.getName());
		}
		//2、执行查询  
		List<Customer> list = customerMapper.findCustomerContribution(map);
		//3、取分页后结果  
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse<List<CustomerComposition>> findCustomerComposition() {
		if(customerMapper.findCustomerComposition().size() != 0){
			return SeverResponse.createSuccess("查询成功", customerMapper.findCustomerComposition());
		}
		return SeverResponse.createError("查询失败");
	}

	@Override
	public SeverResponse<List<CustomerServiceAnalysis>> customerServiceAnalysis() {
		if(customerMapper.customerServiceAnalysis().size() != 0){
			return SeverResponse.createSuccess("查询成功",customerMapper.customerServiceAnalysis());
		}
		return SeverResponse.createError("查询失败");
	}
}
