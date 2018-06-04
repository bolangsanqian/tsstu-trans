package com.tsstu.front.core.exception;
public enum ExceptionCodeEmum {
	BUSINESS("业务异常", 5001), LOGINTIMEOUT("登录超时异常", 5002), SYSTEM("系统异常", 5003), VALIDATE("验证异常", 5004), AUTHORITY("权限遗产", 5005);
	// 成员变量
	private String name;
	private int code;
	// 构造方法
	private ExceptionCodeEmum(String name, int code) {
		this.name = name;
		this.code = code;
	}
	// 普通方法
	public static String getName(int code) {
		for (ExceptionCodeEmum c : ExceptionCodeEmum.values()) {
			if (c.getCode() == code) {
				return c.name;
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}