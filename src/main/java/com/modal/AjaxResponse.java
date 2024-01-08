package com.modal;

public class AjaxResponse {
	
	
	public int responseCode = 0;
	
	public String responseString = new String();
	

	public AjaxResponse(){
		
	}


	public int getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseString() {
		return responseString;
	}


	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
