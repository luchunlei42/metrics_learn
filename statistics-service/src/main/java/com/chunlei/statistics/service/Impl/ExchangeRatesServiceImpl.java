package com.chunlei.statistics.service.Impl;

import com.chunlei.statistics.client.ExchangeRatesClient;
import com.chunlei.statistics.domain.Currency;
import com.chunlei.statistics.domain.ExchangeRatesContainer;
import com.chunlei.statistics.service.ExchangeRatesService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import java.util.Map;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private ExchangeRatesContainer container;
    @Autowired
    private ExchangeRatesClient client;


    @Override
    public Map<Currency, BigDecimal> getCurrentRates() {
        if(container == null || !container.getDate().equals(LocalDate.now())){
            container = client.getRates(Currency.getBase());
        }
        return ImmutableMap.of(
                Currency.EUR,container.getRates().get(Currency.EUR.name()),
                Currency.USD,container.getRates().get(Currency.USD.name()),
                Currency.RMB,BigDecimal.ONE
        );
    }

    @Override
    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        Map<Currency,BigDecimal> rates = getCurrentRates();
        BigDecimal ratio = rates.get(to).divide(rates.get(from),4, RoundingMode.HALF_DOWN);
        return amount.multiply(ratio);
    }
}
