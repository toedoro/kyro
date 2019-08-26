package com.chowis.kyro.model;

import java.util.Arrays;

public enum Permission {
	
	USER(0), 
	ADMIN(1),
	MANAGER(2);
	
	private int code;
	
	Permission(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static Permission getContentType(int code){
		return Arrays.stream(values())
					.filter(c -> c.getCode() == code)
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown enum type %s, Allowed values are %s", code, Arrays.toString(values()))));
	}
	
}
