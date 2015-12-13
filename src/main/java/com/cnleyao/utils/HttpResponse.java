package com.cnleyao.utils;

public class HttpResponse {
	
	public static final int SC_OK                    = 200;
	public static final int SC_BAD_REQUEST           = 400;
	public static final int SC_UNAUTHORIZED          = 401;
	public static final int SC_FORBIDDEN             = 403;
	public static final int SC_NOT_FOUND             = 404;
	public static final int SC_NOT_ACCEPTABLE        = 406;
	public static final int SC_CONFLICT              = 409;
	public static final int SC_INTERNAL_SERVER_ERROR = 500;
	
	private int statusCode;
	private String response;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("statusCode:").append(statusCode).append(", ");
		sb.append("response:").append(response);
		sb.append("}");
		return sb.toString();
	}
	
	public boolean isOk() {
		return (statusCode == 200);
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

}
