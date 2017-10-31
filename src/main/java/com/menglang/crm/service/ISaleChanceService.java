package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.SaleChance;

public interface ISaleChanceService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param saleChance 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, SaleChance saleChance);
	/**
	 * 添加saleChance
	 * @param saleChance saleChance的信息
	 * @return
	 */
	SeverResponse addSaleChance(SaleChance saleChance);
	/**
	 * 删除saleChance（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个saleChance的信息  再做修改
	 * @param id 修改saleChance的id
	 * @return
	 */
	SaleChance findById(Integer id);
	/**
	 * 修改saleChance
	 * @param saleChance saleChance的数据
	 * @return
	 */
	SeverResponse update(SaleChance saleChance);

}
