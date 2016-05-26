package com.eiva.propuesta01.api.dtos;

public class ApiResponseDto {

	public Object response = null;
	public Boolean ok = null;
	public String message = null;
	
	public ApiResponseDto(Object response) {
		this.response = response;
		this.ok = true;
		this.message = null;
	}
	
	public ApiResponseDto(String message) {
		this.response = null;
		this.ok = false;
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public Boolean getOk() {
		return ok;
	}

	public String getMessage() {
		return message;
	}
}
