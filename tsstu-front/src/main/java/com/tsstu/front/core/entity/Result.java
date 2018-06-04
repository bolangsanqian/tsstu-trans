package com.tsstu.front.core.entity;

import java.io.Serializable;

/**
 * 响应结果对象
 * @author liwei
 * @since 2017年4月15日20:41:44
 */
public class Result implements Serializable {
    
	private static final long serialVersionUID = 1L;

	/** 
	 * 成功的状态码
	 */
	public static final int SUCCESS_CODE = 200;
	
	/**
	 * 错误的状态码
	 */
	public static final int ERROR_CODE = 500;
	
    /**
     * 消息
     */
    private String msg = "";

    /**
     * 状态码
     */
    private int status = 0;
    
    /**
     * 数据对象
     */
    private Object data = "";
    
    public Result() {
    	this.data = "";
    }

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public Result setStatus(int status) {
		this.status = status;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}
	
	public Result message() {
		this.status = Result.SUCCESS_CODE;
		this.msg = "操作成功！";
		this.data = "";
		return this;
	}
	
	public Result message(String msg) {
		this.status = Result.SUCCESS_CODE;
		this.msg = msg;
		this.data = "";
		return this;
	}
	
	public Result message(int status, String msg) {
		this.status = status;
		this.msg = msg;
		this.data = "";
		return this;
	}
	
	public Result message(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public Result success() {
		this.status = Result.SUCCESS_CODE;
		this.msg = "操作成功！";
		this.data = "";
		return this;
	}
	
	public Result success(String msg) {
		this.status = Result.SUCCESS_CODE;
		this.msg = msg;
		this.data = "";
		return this;
	}
	
	public Result success(Object data) {
		this.status = Result.SUCCESS_CODE;
		this.msg = "success";
		this.data = data;
		return this;
	}
	
	public Result success(String msg, Object data) {
		this.status = Result.SUCCESS_CODE;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public Result success(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public Result error() {
		this.status = Result.ERROR_CODE;
		this.msg = "操作失败！";
		this.data = "";
		return this;
	}
	
	public Result error(String msg) {
		this.status = Result.ERROR_CODE;
		this.msg = msg;
		this.data = "";
		return this;
	}
	
	public Result error(Object data) {
		this.status = Result.ERROR_CODE;
		this.msg = "error";
		this.data = "";
		return this;
	}
	
	public Result error(String msg, Object data) {
		this.status = Result.ERROR_CODE;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public Result error(int status, String msg) {
		this.status = status;
		this.msg = msg;
		this.data = "";
		return this;
	}
	
	public Result error(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public boolean isOK(){
		return this.status == Result.SUCCESS_CODE;
	}


}
