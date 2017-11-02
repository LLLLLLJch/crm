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
import com.menglang.crm.mapper.SaleChanceMapper;
import com.menglang.crm.pojo.SaleChance;
import com.menglang.crm.pojo.SaleChanceExample;
import com.menglang.crm.pojo.SaleChanceExample.Criteria;
import com.menglang.crm.service.ISaleChanceService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class SaleChanceServiceImpl implements ISaleChanceService{

	@Autowired
	private SaleChanceMapper saleChanceMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,SaleChance saleChance,
			String startDate,String endDate) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		Date startTime = null;
		Date endTime = null;
		String start = null;
		String end = null;
		if(StringUtils.isNotBlank(startDate) & StringUtils.isNotBlank(endDate)){
			start = startDate.replace("/", "-");
			end = endDate.replace("/", "-");
			System.out.println(start);
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				startTime = simpleDateFormat.parse(start);
				endTime = simpleDateFormat.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		SaleChanceExample example = new SaleChanceExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(saleChance.getCustomerName())){
			criteria.andCustomerNameLike(LikeNameUtil.formartLike(saleChance.getCustomerName()));
		}
		if(StringUtils.isNotBlank(saleChance.getOverview())){
			criteria.andOverviewLike(LikeNameUtil.formartLike(saleChance.getOverview()));
		}
		if(StringUtils.isNotBlank(saleChance.getCreateMan())){
			criteria.andCreateManLike(LikeNameUtil.formartLike(saleChance.getCreateMan()));
		}
		if(startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
			criteria.andCreateTimeBetween(start, end);
		}
		if(StringUtils.isNotBlank(saleChance.getCreateMan())){
			criteria.andCreateManLike(LikeNameUtil.formartLike(saleChance.getCreateMan()));
		}
		if(saleChance.getStatus()!=null){
			criteria.andStatusEqualTo(saleChance.getStatus());
		}
		//2、执行查询  
		List<SaleChance> list = saleChanceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addSaleChance(SaleChance saleChance) {
		SaleChanceExample example = new SaleChanceExample();
		if(StringUtils.isNotBlank(saleChance.getAssignMan())){
			saleChance.setStatus(1);
		}else {
			saleChance.setStatus(0);
		}
		saleChance.setDevResult(0);
		if(saleChanceMapper.insert(saleChance)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public SaleChance findById(Integer id) {
		return saleChanceMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(SaleChance saleChance) {
		if(saleChanceMapper.updateByPrimaryKey(saleChance)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public List<SaleChance> findAssignMan() {
		return saleChanceMapper.findAssignMan();
	}

	@Override
	public EasyuiDataGridResult findStatusIsOne(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		SaleChanceExample example = new SaleChanceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		//2、执行查询  
		List<SaleChance> list = saleChanceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse stopDevelopment(Integer id) {
		SaleChance saleChance = new SaleChance();
		saleChance.setId(id);
		saleChance.setDevResult(3);
		if(saleChanceMapper.updateByPrimaryKey(saleChance)>0){
			return SeverResponse.createSuccess("终止成功");
		}
		return SeverResponse.createError("终止失败");
	}
}
