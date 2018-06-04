package com.tsstu.console.core.exception;
/**
 * 权限异常
 * @author liwei
 * 日期：2017年4月14日14:25:12
 */
public class AuthorityException extends BaseException {

	private static final long serialVersionUID = 1L;

	public AuthorityException() {
		super();
	}

	public AuthorityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthorityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorityException(String message) {
		super(message);
	}
	
	public AuthorityException(String message, Object...objs) {
		super(message);
	}

	public AuthorityException(Throwable cause) {
		super(cause);
	}
}
