package com.dguzowski.currency.exchange.converter;

import com.dguzowski.currency.exchange.converter.model.CurrencyData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrencyDataValidator {

    public boolean isCurrencyDataValid(CurrencyData currencyData) {
        return currencyData != null && !currencyData.getRates().isEmpty();
    }

    public boolean isCurrencyDataUpToDate(CurrencyData currencyData) {
        LocalDate lastDate = currencyData.getRates().get(0).getEffectiveDate();
        return lastDate.isBefore(LocalDate.now());
    }
}
