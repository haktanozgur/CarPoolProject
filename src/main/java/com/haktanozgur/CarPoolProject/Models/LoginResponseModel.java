package com.haktanozgur.CarPoolProject.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseModel {

	private String token;
	
	private Integer httpStatusCode;

	private boolean isSuccess;
	
	private String message;

	

	@JsonProperty("StatusCode")
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	@JsonProperty("Success")
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	@JsonProperty("Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@JsonProperty("Token")
	public String getToken() {
		return token;
	}

	public void setResult(String token) {
		this.token = token;
	}

	public static LoginResponseModel success() {
		LoginResponseModel result = new LoginResponseModel();
		result.setSuccess(true);
		result.setHttpStatusCode(200);
		return result;
	}
	
	public static LoginResponseModel fail() {
		LoginResponseModel result = new LoginResponseModel();
		result.setSuccess(false);
		result.setHttpStatusCode(500);
		return result;
	}
	
	public static LoginResponseModel fail(Integer statusCode) {
		LoginResponseModel result = new LoginResponseModel();
		result.setSuccess(false);
		result.setHttpStatusCode(statusCode);
		return result;
	}
	
	public static LoginResponseModel wrongInformation() {
		LoginResponseModel result = new LoginResponseModel();
		result.setSuccess(false);
		result.setHttpStatusCode(400);
		result.setMessage("Girilen bilgiler yanlıştır");
		return result;	
	}

	public LoginResponseModel httpStatusCode(Integer statuscode) {
		this.httpStatusCode = statuscode;
		return this;
	}

	public LoginResponseModel message(String message) {
		this.message = message;
		return this;
	}

	public LoginResponseModel token(String token) {
		this.token = token;
		return this;
	}	
	
}

