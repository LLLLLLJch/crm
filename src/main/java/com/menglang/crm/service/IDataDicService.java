package com.menglang.crm.service;

import java.util.List;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.DataDic;

public interface IDataDicService {
	/**
	 * 查新+分页
	 * @param page 当前页
	 * @param rows 每页显示的数量
	 * @param dataDic 查询条件
	 * @return
	 */
	EasyuiDataGridResult findAll(Integer page, Integer rows, DataDic dataDic);
	/**
	 * 添加dataDic
	 * @param dataDic dataDic的信息
	 * @return
	 */
	SeverResponse addDataDic(DataDic dataDic);
	/**
	 * 删除dataDic（支持批量删除和单个删除）
	 * @param ids 想要删除数据的id
	 * @return
	 */
	SeverResponse delete(String ids);
	/**
	 * 根据id查询出信息 用于修改的时候先查处这个dataDic的信息  再做修改
	 * @param id 修改dataDic的id
	 * @return
	 */
	DataDic findById(Integer id);
	/**
	 * 修改dataDic
	 * @param dataDic dataDic的数据
	 * @return
	 */
	SeverResponse update(DataDic dataDic);
	List<DataDic> findDataDicName();
	List<DataDic> findLevel();
	List<DataDic> findServiceType();

}
