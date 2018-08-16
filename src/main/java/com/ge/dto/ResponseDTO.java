package com.ge.dto;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable {

	private static final long	serialVersionUID	= 2524502858249308612L;
	private String				message;
	private T				responseData;

	
	public ResponseDTO( String message, T responseData) {
		super();
		this.message = message;
		this.responseData = responseData;
	}
	

	public ResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	

	public T getResponseData() {

		return responseData;
	}

	public void setResponseData(T responseData) {

		this.responseData = responseData;
	}

	

}
