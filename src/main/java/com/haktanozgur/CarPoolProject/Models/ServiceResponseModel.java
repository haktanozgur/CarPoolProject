package com.haktanozgur.CarPoolProject.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseModel {

	private Integer httpStatusCode;

	private boolean isSuccess;
	
	private String message;

	private Object result;
	

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
	@JsonProperty("Result")
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public static ServiceResponseModel success() {
		ServiceResponseModel result = new ServiceResponseModel();
		result.setSuccess(true);
		result.setHttpStatusCode(200);
		return result;
	}
	
	public static ServiceResponseModel fail() {
		ServiceResponseModel result = new ServiceResponseModel();
		result.setSuccess(false);
		result.setHttpStatusCode(500);
		return result;
	}
	
	public static ServiceResponseModel fail(Integer statusCode) {
		ServiceResponseModel result = new ServiceResponseModel();
		result.setSuccess(false);
		result.setHttpStatusCode(statusCode);
		return result;
	}

	public ServiceResponseModel httpStatusCode(Integer statuscode) {
		this.httpStatusCode = statuscode;
		return this;
	}

	public ServiceResponseModel message(String message) {
		this.message = message;
		return this;
	}

	public ServiceResponseModel result(Object result) {
		this.result = result;
		return this;
	}	
	
}
