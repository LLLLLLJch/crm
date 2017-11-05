package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CustomerLossMeasure;

public interface ICustomerLossMeasureService {

	EasyuiDataGridResult findAll(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure);

	int add(CustomerLossMeasure customerLossMeasure);

	int update(CustomerLossMeasure customerLossMeasure);

	int delete(Integer id);

}
