package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.pojo.CustomerContact;

public interface ICustomerContactService {

	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerContact customerContact);

	int add(CustomerContact customerContact);

	int update(CustomerContact customerContact);

	int delete(Integer id);

}
