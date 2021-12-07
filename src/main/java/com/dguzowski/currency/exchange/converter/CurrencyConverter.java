package com.dguzowski.currency.exchange.converter;

import com.dguzowski.currency.exchange.helpers.CurrencyCode;

import java.math.BigDecimal;

public interface CurrencyConverter {
    BigDecimal convert(BigDecimal amount, CurrencyCode from);
}
