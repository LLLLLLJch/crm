package com.menglang.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.UserMapper;
import com.menglang.crm.pojo.User;
import com.menglang.crm.pojo.UserExample;
import com.menglang.crm.pojo.UserExample.Criteria;
import com.menglang.crm.service.IUserService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,User user) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(user.getUserName())){
			criteria.andUserNameLike(LikeNameUtil.formartLike(user.getUserName()));
		}
		//2、执行查询  
		List<User> list = userMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<User> pageInfo = new PageInfo<User>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addUser(User user) {
		UserExample example = new UserExample();
		if(userMapper.insert(user)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(User user) {
		if(userMapper.updateByPrimaryKey(user)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}
}
