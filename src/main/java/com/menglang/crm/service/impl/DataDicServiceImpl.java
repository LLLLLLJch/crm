package com.menglang.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.DataDicMapper;
import com.menglang.crm.pojo.DataDic;
import com.menglang.crm.pojo.DataDicExample;
import com.menglang.crm.pojo.DataDicExample.Criteria;
import com.menglang.crm.service.IDataDicService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class DataDicServiceImpl implements IDataDicService{

	@Autowired
	private DataDicMapper dataDicMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,DataDic dataDic) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		DataDicExample example = new DataDicExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(dataDic.getDataDicName())){
			criteria.andDataDicNameLike(LikeNameUtil.formartLike(dataDic.getDataDicName()));
		}
		if(StringUtils.isNotBlank(dataDic.getDataDicValue())){
			criteria.andDataDicValueLike(LikeNameUtil.formartLike(dataDic.getDataDicValue()));
		}
		//2、执行查询  
		List<DataDic> list = dataDicMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<DataDic> pageInfo = new PageInfo<DataDic>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addDataDic(DataDic dataDic) {
		DataDicExample example = new DataDicExample();
		if(dataDicMapper.insert(dataDic)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = dataDicMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public DataDic findById(Integer id) {
		return dataDicMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(DataDic dataDic) {
		if(dataDicMapper.updateByPrimaryKey(dataDic)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public List<DataDic> findDataDicName() {
		return dataDicMapper.findDataDicName();
	}
}
