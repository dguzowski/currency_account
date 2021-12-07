package com.dguzowski.currency.exchange.converter;

import com.dguzowski.currency.exchange.converter.model.CurrencyData;

import java.util.Optional;

public interface CurrencyDataProvider {
    Optional<CurrencyData> getCurrentDollarData();
}
