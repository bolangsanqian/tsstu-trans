package com.tsstu.front.core.exception;
/**
 * 验证异常
 * @author liwei
 * 日期：2017年4月14日14:25:12
 */
public class ValidateException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ValidateException() {
		super();
	}

	public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}


}
