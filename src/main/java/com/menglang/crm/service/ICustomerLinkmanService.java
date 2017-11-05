package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.pojo.CustomerLinkman;

public interface ICustomerLinkmanService {

	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLinkman customerLinkman);

	int add(CustomerLinkman customerLinkman);

	int update(CustomerLinkman customerLinkman);

	int delete(Integer id);

}
