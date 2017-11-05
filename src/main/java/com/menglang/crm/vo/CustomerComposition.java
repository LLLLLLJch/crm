package com.menglang.crm.vo;

public class CustomerComposition {

	private String customerLevel;
	private Integer num;
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CustomerComposition [customerLevel=" + customerLevel + ", num=" + num + "]";
	}
	
}
