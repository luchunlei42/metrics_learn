package com.chunlei.statistics.client;

import com.chunlei.statistics.domain.Currency;
import com.chunlei.statistics.domain.ExchangeRatesContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;

@FeignClient(url = "${rates.url}", name = "rates-client",fallback = ExchangeRatesClientFallback.class)
public interface ExchangeRatesClient {
    @GetMapping("/latest")
    ExchangeRatesContainer getRates(@RequestParam("base")Currency base);
}
