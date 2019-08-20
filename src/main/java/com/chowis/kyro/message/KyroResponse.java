package com.chowis.kyro.message;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class KyroResponse {
	
	private BigInteger id;
	private String message;

	public KyroResponse() {
	}
	
	public static KyroResponse of(String message){
		return new KyroResponse(message);
	}

	public BigInteger getId() {
		return id;
	}

	public KyroResponse setId(BigInteger id) {
		this.id = id;
		return this;
	}

	public KyroResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public KyroResponse setMessage(String message) {
		this.message = message;
		return this;
	}

}
