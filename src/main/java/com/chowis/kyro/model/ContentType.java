package com.chowis.kyro.model;

import java.util.Arrays;

public enum ContentType {
	
	IMAGE_JPEG("image/jpeg", 0), VIDEO_MP4("video/mp4", 1);
	
	private String code;
	private int value;
	
	ContentType(String code, int value){
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public int getValue() {
		return value;
	}
	
	public static ContentType getContentType(String code){
		return Arrays.stream(values())
					.filter(c -> c.getCode().equals(code))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown enum type %s, Allowed values are %s", code, Arrays.toString(values()))));
	}
	
}
