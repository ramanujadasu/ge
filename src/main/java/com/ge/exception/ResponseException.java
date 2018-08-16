package com.ge.exception;

import javax.servlet.ServletException;

import com.ge.dto.ResponseDTO;

public class ResponseException extends ServletException {

	private static final long serialVersionUID = -7994190589411356111L;
	private String message;
	private String responseData = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public ResponseException(String message, String responseData) {
		message = this.message;
		responseData = this.responseData;
	}

	public static ResponseDTO getTokenExceptionDTO(String errorMessage) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResponseData(null);
		responseDTO.setMessage(errorMessage);
		return responseDTO;
	}

}
