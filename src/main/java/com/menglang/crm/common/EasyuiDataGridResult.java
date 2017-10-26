package com.menglang.crm.common;

import java.io.Serializable;
import java.util.List;

public class EasyuiDataGridResult implements Serializable{

	private Integer total;
	private List<?> rows;
	
	public EasyuiDataGridResult() {
		super();
	}
	public EasyuiDataGridResult(Integer total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "EasyuiDataGridResult [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
