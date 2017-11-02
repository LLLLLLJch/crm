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
		if(StringUtils.isNotBlank(user.getName())){
			criteria.andNameLike(LikeNameUtil.formartLike(user.getName()));
		}
		if(StringUtils.isNotBlank(user.getPassword())){
			criteria.andPasswordLike(LikeNameUtil.formartLike(user.getPassword()));
		}
		if(StringUtils.isNotBlank(user.getEmail())){
			criteria.andEmailLike(LikeNameUtil.formartLike(user.getEmail()));
		}
		if(StringUtils.isNotBlank(user.getTrueName())){
			criteria.andTrueNameLike(LikeNameUtil.formartLike(user.getTrueName()));
		}
		if(StringUtils.isNotBlank(user.getPhone())){
			criteria.andPhoneLike(LikeNameUtil.formartLike(user.getPhone()));
		}
		if(StringUtils.isNotBlank(user.getRoleName())){
			criteria.andRoleNameLike(LikeNameUtil.formartLike(user.getRoleName()));
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

	@Override
	public SeverResponse checkName(String name) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameEqualTo(name);
		}
		if(userMapper.countByExample(example)>0){
			return SeverResponse.createSuccess();
		}
		return SeverResponse.createError();
	}

	@Override
	public SeverResponse checkPasswordByName(String name, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameEqualTo(name);
		}
		if(StringUtils.isNotBlank(password)){
			criteria.andPasswordEqualTo(password);
		}
		if(userMapper.countByExample(example)>0){
			return SeverResponse.createSuccess();
		}
		return SeverResponse.createError();
	}

	@Override
	public SeverResponse surePassword(String newPassword, String surePassword) {
		String newWord = null;
		String sureWord = null;
		if(newPassword != null && newPassword != ""){
			newWord = newPassword;
		}
		if(surePassword != null && surePassword != ""){
			sureWord = surePassword;
		}
		if(newWord.equals(sureWord)){
			return SeverResponse.createSuccess();
		}
		return SeverResponse.createError();
	}

	@Override
	public SeverResponse updatePassword(User user,String newPassword) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(user.getName())){
			criteria.andNameEqualTo(user.getName());
		}
		if(StringUtils.isNotBlank(user.getPassword())){
			criteria.andPasswordEqualTo(user.getPassword());
		}
		User user2 = new User();
		user2.setPassword(newPassword);
		if(userMapper.updateByExampleSelective(user2, example)>0){
			return SeverResponse.createSuccess("修改成功");
		}
		return SeverResponse.createError("修改失败");
	}

	@Override
	public User checkUser(String name, String password, String roleName) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameEqualTo(name);
		}
		if(StringUtils.isNotBlank(password)){
			criteria.andPasswordEqualTo(password);
		}
		if(StringUtils.isNotBlank(roleName)){
			criteria.andRoleNameEqualTo(roleName);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		map.put("roleName", roleName);
		return userMapper.checkName(map);
	}

	@Override
	public List<User> findManger() {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo("客户经理");
		return userMapper.selectByExample(example);
	}
}
