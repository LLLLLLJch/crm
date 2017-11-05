package com.menglang.crm.service;

import java.util.List;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerService;

public interface ICustomerServiceService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param customerService 查询条件
	 * @param endDate 
	 * @param startDate 
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerService customerService, String startDate, String endDate);
	/**
	 * 添加customerService
	 * @param customerService customerService的信息
	 * @return
	 */
	SeverResponse add(CustomerService customerService);
	/**
	 * 删除customerService（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个customerService的信息  再做修改
	 * @param id 修改customerService的id
	 * @return
	 */
	CustomerService findById(Integer id);
	/**
	 * 修改customerService
	 * @param customerService customerService的数据
	 * @return
	 */
	SeverResponse update(CustomerService customerService);
	List<CustomerService> findAssignMan();
	EasyuiDataGridResult findStatusIsOne(Integer page, Integer rows);
	SeverResponse stopDevelopment(Integer id);
	EasyuiDataGridResult findNotDel(Integer page, Integer rows, CustomerService customerService);
	SeverResponse process(CustomerService customerService);
	EasyuiDataGridResult findProcessed(Integer page, Integer rows, CustomerService customerService, String startDate,
			String endDate);
	SeverResponse serviceFeedback(CustomerService customerService);
	EasyuiDataGridResult findArchive(Integer page, Integer rows, CustomerService customerService, String startDate,
			String endDate);
	CustomerService findServiceDetail(Integer id);

}
