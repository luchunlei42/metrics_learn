package com.chunlei.statistics.domain;

public enum Currency {

	USD, EUR, RMB;

	public static Currency getBase() {
		return RMB;
	}
}
