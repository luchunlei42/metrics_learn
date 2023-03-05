package com.chunlei;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumConverter implements Converter<String,Currency> {
    @Override
    public Currency convert(String source) {
        return Currency.valueOf(source);
    }
}
