package com.menglang.crm.common;

import java.io.Serializable;

/*
 * 后台返回给前端的对象
 */
public class SeverResponse<T> implements Serializable {

	// 当前状态，给程序员看得；成功，失败，未登录，未获得权限
	private int status;
	// 返回提示信息，给用户看得
	private String msg;
	// 后台返回给前台的数据
	private T data;

	public SeverResponse() {
		super();
	}

	public SeverResponse(int status) {
		super();
		this.status = status;
	}

	public SeverResponse(int status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	public SeverResponse(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public SeverResponse(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	// 只是告诉前台：成功这个状态
	public static <T> SeverResponse<T> createSuccess() {
		return new SeverResponse<>(ResponseCode.Success.getCode());
	}

	// 只是告诉前台：status,msg
	public static <T> SeverResponse<T> createSuccess(String msg) {
		return new SeverResponse<>(ResponseCode.Success.getCode(), msg);
	}

	// 只是告诉前台：status,msg,data
	public static <T> SeverResponse<T> createSuccess(String msg, T data) {
		return new SeverResponse<>(ResponseCode.Success.getCode(), msg, data);
	}

	// 只是告诉前台：失败这个状态
	public static <T> SeverResponse<T> createError() {
		return new SeverResponse<>(ResponseCode.ERROR.getCode());
	}

	// 只是告诉前台：status,msg
	public static <T> SeverResponse<T> createError(String msg) {
		return new SeverResponse<>(ResponseCode.ERROR.getCode(), msg);
	}

	// 只是告诉前台：status,msg,data
	public static <T> SeverResponse<T> createError(String msg, T data) {
		return new SeverResponse<>(ResponseCode.ERROR.getCode(), msg, data);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
