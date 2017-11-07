package com.menglang.crm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.CustomerServiceMapper;
import com.menglang.crm.pojo.CustomerService;
import com.menglang.crm.pojo.CustomerServiceExample;
import com.menglang.crm.pojo.CustomerServiceExample.Criteria;
import com.menglang.crm.service.ICustomerServiceService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class CustomerServiceServiceImpl implements ICustomerServiceService{

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,CustomerService customerService,
			String startDate,String endDate) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerServiceExample example = new CustomerServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("新创建");
		//2、执行查询  
		List<CustomerService> list = customerServiceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse add(CustomerService customerService) {
		if(customerServiceMapper.insert(customerService)>0){
			return SeverResponse.createSuccess("创建成功");
		}
		return SeverResponse.createError("创建失败");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = customerServiceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public CustomerService findById(Integer id) {
		return customerServiceMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(CustomerService customerService) {
		customerService.setStatus("已分配");
		if(customerServiceMapper.updateByPrimaryKey(customerService)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public EasyuiDataGridResult findStatusIsOne(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerServiceExample example = new CustomerServiceExample();
		Criteria criteria = example.createCriteria();
		//2、执行查询  
		List<CustomerService> list = customerServiceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse stopDevelopment(Integer id) {
		CustomerService customerService = new CustomerService();
		customerService.setId(id);
		if(customerServiceMapper.updateByPrimaryKey(customerService)>0){
			return SeverResponse.createSuccess("终止成功");
		}
		return SeverResponse.createError("终止失败");
	}

	@Override
	public List<CustomerService> findAssignMan() {
		return null;
	}

	@Override
	public EasyuiDataGridResult findNotDel(Integer page, Integer rows, CustomerService customerService) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerServiceExample example = new CustomerServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("已分配");
		//2、执行查询  
		List<CustomerService> list = customerServiceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse process(CustomerService customerService) {
		customerService.setStatus("已处理");
		if(customerServiceMapper.updateByPrimaryKey(customerService)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public EasyuiDataGridResult findProcessed(Integer page, Integer rows, CustomerService customerService,
			String startDate, String endDate) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerServiceExample example = new CustomerServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("已处理");
		//2、执行查询  
		List<CustomerService> list = customerServiceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse serviceFeedback(CustomerService customerService) {
		customerService.setStatus("已归档");
		if(customerServiceMapper.updateByPrimaryKey(customerService)>0){
			return SeverResponse.createSuccess("反馈成功");
		}
		return SeverResponse.createError("反馈失败");
	}

	@Override
	public EasyuiDataGridResult findArchive(Integer page, Integer rows, CustomerService customerService,
			String startDate, String endDate) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		CustomerServiceExample example = new CustomerServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("已归档");
		if(StringUtils.isNotBlank(customerService.getCustomer())){
			criteria.andCustomerLike(LikeNameUtil.formartLike(customerService.getCustomer()));
		}
		if(StringUtils.isNotBlank(customerService.getOverview())){
			criteria.andOverviewEqualTo(customerService.getOverview());
		}
		if(StringUtils.isNotBlank(customerService.getServiceType())){
			criteria.andServiceTypeEqualTo(customerService.getServiceType());
		}
		if(StringUtils.isNotBlank(startDate) & StringUtils.isNotBlank(endDate)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = null;
			Date end = null;
			try {
				start = simpleDateFormat.parse(startDate);
				end = simpleDateFormat.parse(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			criteria.andCreateTimeBetween(start, end);
		}
		//2、执行查询  
		List<CustomerService> list = customerServiceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public CustomerService findServiceDetail(Integer id) {
		return customerServiceMapper.selectByPrimaryKey(id);
	}
}
