package com.WebServer.http;
/**
 * 空请求异常
 * @author soft01
 *
 */
public class EmptyRequestExtion extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyRequestExtion() {
		super();
	}

	public EmptyRequestExtion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyRequestExtion(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyRequestExtion(String message) {
		super(message);
	}

	public EmptyRequestExtion(Throwable cause) {
		super(cause);
	}
	
	
       
}
