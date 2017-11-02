package com.menglang.crm.service;

import java.util.List;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.User;

public interface IUserService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param user 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, User user);
	/**
	 * 添加user
	 * @param user user的信息
	 * @return
	 */
	SeverResponse addUser(User user);
	/**
	 * 删除user（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个user的信息  再做修改
	 * @param id 修改user的id
	 * @return
	 */
	User findById(Integer id);
	/**
	 * 修改user
	 * @param user user的数据
	 * @return
	 */
	SeverResponse update(User user);
	SeverResponse checkName(String name);
	SeverResponse checkPasswordByName(String name, String password);
	SeverResponse surePassword(String newPassword, String surePassword);
	SeverResponse updatePassword(User user, String newPassword);
	User checkUser(String name, String password, String roleName);
	List<User> findManger();

}
