package com.menglang.crm.common;

public enum ResponseCode {

	Success(0,"Success"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(2,"NEED_LOGIN");
	
	private int code;
	private String desc;
	
	private ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
}
