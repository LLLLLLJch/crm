package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.Product;

public interface IProductService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param product 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, Product product);
	/**
	 * 添加product
	 * @param product product的信息
	 * @return
	 */
	SeverResponse addProduct(Product product);
	/**
	 * 删除product（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个product的信息  再做修改
	 * @param id 修改product的id
	 * @return
	 */
	Product findById(Integer id);
	/**
	 * 修改product
	 * @param product product的数据
	 * @return
	 */
	SeverResponse update(Product product);

}
