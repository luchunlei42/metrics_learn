package com.chunlei;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class exchangerateController {

    @GetMapping("/latest")
    ExchangeRatesContainer getRates(@RequestParam("base")Currency base){
        ExchangeRatesContainer container = new ExchangeRatesContainer();
        container.setDate(LocalDate.now());
        Map<String, BigDecimal> rates = new HashMap<>();
        if(base == Currency.RMB){
            rates.put(Currency.RMB.name(), BigDecimal.valueOf(1));
            rates.put(Currency.EUR.name(), BigDecimal.valueOf(0.136315));
            rates.put("USD", BigDecimal.valueOf(0.147599));
        }else if(base == Currency.EUR){
            rates.put(Currency.RMB.name(), BigDecimal.valueOf(7.335933));
            rates.put(Currency.EUR.name(), BigDecimal.valueOf(1));
            rates.put("USD", BigDecimal.valueOf(1.082778));
        }else {
            rates.put(Currency.RMB.name(), BigDecimal.valueOf(6.775104));
            rates.put(Currency.EUR.name(), BigDecimal.valueOf(0.92355));
            rates.put("USD", BigDecimal.valueOf(1));
        }
        container.setRates(rates);
        container.setBase(base);
        return container;
    }

}
