package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerLoss;

public interface ICustomerLossService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param customerLoss 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLoss customerLoss);
	/**
	 * 添加customerLoss
	 * @param customerLoss customerLoss的信息
	 * @return
	 */
	SeverResponse addCustomerLoss(CustomerLoss customerLoss);
	/**
	 * 删除customerLoss（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个customerLoss的信息  再做修改
	 * @param id 修改customerLoss的id
	 * @return
	 */
	CustomerLoss findById(Integer id);
	/**
	 * 修改customerLoss
	 * @param customerLoss customerLoss的数据
	 * @return
	 */
	SeverResponse update(CustomerLoss customerLoss);
	SeverResponse confirmLoss(CustomerLoss customerLoss);

}
