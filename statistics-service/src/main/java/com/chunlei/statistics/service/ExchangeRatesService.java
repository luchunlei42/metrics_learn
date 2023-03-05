package com.chunlei.statistics.service;

import com.chunlei.statistics.domain.Currency;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeRatesService {
    /*
    requests today's foreign exchange rates from a provider
    or reuses values from the last request (if they are still relevant)
     */
    Map<Currency, BigDecimal> getCurrentRates();
    /*
    convert given amount to specified currency
     */
    BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
