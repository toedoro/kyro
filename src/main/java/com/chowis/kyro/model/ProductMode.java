package com.chowis.kyro.model;

import java.util.Arrays;

public enum ProductMode {
	
	OILY("oily", 1),
	GOOD("good", 2),
	DRY("dry", 3),
	COMPLEXION("complexion", 4),
	WRINKLES("wrinkles", 5),
	IMPURITIES("impurities", 6),
	KERATIN("keratin", 7),
	MOISTURE("moisture", 8),
	PORES("pores", 9),
	SPOTS("spots", 10);
	
	private String code;
	private int value;
	
	ProductMode(String code, int value){
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public int getValue() {
		return value;
	}
	
	public static ProductMode getContentType(String code){
		return Arrays.stream(values())
					.filter(c -> c.getCode().equals(code))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.format("Unknown enum type %s, Allowed values are %s", code, Arrays.toString(values()))));
	}
	
}
