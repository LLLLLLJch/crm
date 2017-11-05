package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.pojo.CustomerOrder;

public interface ICustomerOrderService {

	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerOrder customerOrder);

	int add(CustomerOrder customerOrder);

	int update(CustomerOrder customerOrder);

	int delete(Integer id);

}
