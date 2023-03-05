package com.chunlei;

public enum Currency {

	USD, EUR, RMB;

	public static Currency getBase() {
		return RMB;
	}
}
