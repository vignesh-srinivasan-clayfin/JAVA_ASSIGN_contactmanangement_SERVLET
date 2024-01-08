package com.modal.response;

public class Response<T> {

	public T responseBody;

	public String error;

	public String errorCode;
	
	public Response() {
		
	}

	public void setResponseBody(T t) {
		this.responseBody = t;
	}

	public T getResponseBody() {
		return responseBody;
	}

	public void setError(String t) {
		this.error = t;
	}

	public String getError() {
		return error;
	}

	public void setErrorCode(String t) {
		this.errorCode = t;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
