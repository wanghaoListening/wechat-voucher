package com.cnleyao.exception;
/**
 * <p>Title:系统自定义异常类<p>
 * <p>Description:系统自定义异常类，针对预期的异常，需要在程序抛出此类异常<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月20日
 * */
public class CustomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomException(String message){
		this.message = message;
	}

	public CustomException() {
		super();
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
