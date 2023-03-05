package com.chunlei;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class ExchangeRatesContainer {
    private LocalDate date = LocalDate.now();
    private Currency base;
    private Map<String, BigDecimal> rates;
}
