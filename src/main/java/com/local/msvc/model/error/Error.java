package com.local.msvc.model.error;

import io.swagger.annotations.ApiModelProperty;


public class Error {
	
	private String code;
	
	private String message;

	@ApiModelProperty(position = 4, required = true, value = "Error Code",name="Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@ApiModelProperty(position = 5, required = true, value = "Error Message",name="Message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
