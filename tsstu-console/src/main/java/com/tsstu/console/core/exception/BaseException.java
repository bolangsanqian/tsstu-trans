package com.tsstu.console.core.exception;

/**
 * 自定义异常基类
 * @author liwei 
 * 日期：2017年4月14日13:55:50
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
