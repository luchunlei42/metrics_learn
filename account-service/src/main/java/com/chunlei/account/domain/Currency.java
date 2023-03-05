package com.chunlei.account.domain;

public enum Currency {
    USD,EUR,RUB,RMB;

    public static Currency getDefault() {return RMB;}
}
