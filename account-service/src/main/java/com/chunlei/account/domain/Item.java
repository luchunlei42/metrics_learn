package com.chunlei.account.domain;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Item {
    private String title;
    private BigDecimal amount;
    private Currency currency;
    private TimePeriod period;
    private String icon;

}
