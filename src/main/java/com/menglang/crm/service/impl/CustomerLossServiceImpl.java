package com.menglang.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerLossMapper;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.pojo.CustomerLoss;
import com.menglang.crm.pojo.CustomerLossExample;
import com.menglang.crm.pojo.CustomerLossExample.Criteria;
import com.menglang.crm.pojo.CustomerOrder;
import com.menglang.crm.service.ICustomerLossService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class CustomerLossServiceImpl implements ICustomerLossService{

	@Autowired
	private CustomerLossMapper customerLossMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,CustomerLoss customerLoss) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerLossExample example = new CustomerLossExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(customerLoss.getCustomerName())){
			criteria.andCustomerNameLike(LikeNameUtil.formartLike(customerLoss.getCustomerName()));
		}
		if(StringUtils.isNotBlank(customerLoss.getCustomerNo())){
			criteria.andCustomerNoLike(customerLoss.getCustomerNo());
		}
		if(customerLoss.getStatus() != null){
			criteria.andStatusEqualTo(customerLoss.getStatus());
		}
		//2、执行查询  
		List<CustomerLoss> list = customerLossMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerLoss> pageInfo = new PageInfo<CustomerLoss>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addCustomerLoss(CustomerLoss customerLoss) {
		CustomerLossExample example = new CustomerLossExample();
		if(customerLossMapper.insert(customerLoss)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = customerLossMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public CustomerLoss findById(Integer id) {
		return customerLossMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(CustomerLoss customerLoss) {
		if(customerLossMapper.updateByPrimaryKey(customerLoss)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public SeverResponse confirmLoss(CustomerLoss customerLoss) {
		customerLoss.setStatus(1);
		if(customerLossMapper.updateByPrimaryKey(customerLoss)>0){
			return SeverResponse.createSuccess("确认流失成功");
		}
		return SeverResponse.createError("确认流失失败");
	}

}
