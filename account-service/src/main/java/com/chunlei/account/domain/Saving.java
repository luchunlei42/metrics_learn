package com.chunlei.account.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class Saving {
    private BigDecimal amount;

    private Currency currency;

    private BigDecimal interest;

    private Boolean deposit;

    private Boolean capitalization;
}
