package com.local.msvc.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.local.msvc.model.error.Error;

@ApiModel
public class Response {
	
	private String status;
	
	private String response;
	
	private Error error;

	@ApiModelProperty(position = 1, required = true, value = "Success or Failed",name="Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@ApiModelProperty(position =2, value = "Response Payload",name="Response")
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	@ApiModelProperty(position = 3, required = true, value = "Error",name="Error")
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
