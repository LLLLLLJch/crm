package com.menglang.crm.service;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.CusDevPlan;

public interface ICusDevPlanService {

	EasyuiDataGridResult findAll(Integer page, Integer rows, CusDevPlan cusDevPlan);

	int add(CusDevPlan cusDevPlan);

	int update(CusDevPlan cusDevPlan);

	int delete(Integer id);

}
