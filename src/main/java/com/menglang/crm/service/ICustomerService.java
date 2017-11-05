package com.menglang.crm.service;

import java.util.List;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.Customer;
import com.menglang.crm.vo.CustomerComposition;
import com.menglang.crm.vo.CustomerContribution;
import com.menglang.crm.vo.CustomerServiceAnalysis;

public interface ICustomerService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param customer 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, Customer customer);
	/**
	 * 添加customer
	 * @param customer customer的信息
	 * @return
	 */
	SeverResponse addCustomer(Customer customer);
	/**
	 * 删除customer（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个customer的信息  再做修改
	 * @param id 修改customer的id
	 * @return
	 */
	Customer findById(Integer id);
	/**
	 * 修改customer
	 * @param customer customer的数据
	 * @return
	 */
	SeverResponse update(Customer customer);
	void checkCustomerLoss();
	/**
	 * 客户贡献
	 * @param page
	 * @param rows
	 * @param contribution
	 * @return
	 */
	EasyuiDataGridResult findCustomerContribution(Integer page, Integer rows, CustomerContribution contribution);
	/**
	 * 客户构成分析
	 * @return
	 */
	SeverResponse<List<CustomerComposition>> findCustomerComposition();
	SeverResponse<List<CustomerServiceAnalysis>> customerServiceAnalysis();

}
