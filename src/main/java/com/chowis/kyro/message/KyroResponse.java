package com.chowis.kyro.message;

public class KyroResponse {

	private String message;

	public KyroResponse() {
	}

	public KyroResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}