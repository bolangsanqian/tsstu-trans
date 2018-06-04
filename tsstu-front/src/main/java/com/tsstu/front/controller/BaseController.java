package com.tsstu.front.controller;

import com.tsstu.front.core.entity.Result;

public class BaseController {

	public Result message() {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData("");
		return result;
	}
	
	public Result message(String msg) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result message(int status, String msg) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result message(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result success() {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData("");
		return result;
	}
	
	public Result success(String msg) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result success(Object data) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg("操作成功！");
		result.setData(data);
		return result;
	}
	
	public Result success(String msg, Object data) {
		Result result = new Result();
		result.setStatus(Result.SUCCESS_CODE);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result success(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result error() {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg("操作失败！");
		result.setData("");
		return result;
	}
	
	public Result error(String msg) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result error(Object data) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg("操作失败！");
		result.setData(data);
		return result;
	}
	
	public Result error(String msg, Object data) {
		Result result = new Result();
		result.setStatus(Result.ERROR_CODE);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public Result error(int status, String msg) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData("");
		return result;
	}
	
	public Result error(int status, String msg, Object data) {
		Result result = new Result();
		result.setStatus(status);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
}
