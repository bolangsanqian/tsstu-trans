package com.tsstu.console.core.exception;
/**
 * 系统异常
 * @author liwei
 * 日期：2017年4月14日14:24:25
 */
public class SystemException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}
