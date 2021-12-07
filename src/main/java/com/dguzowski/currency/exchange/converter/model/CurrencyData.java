package com.dguzowski.currency.exchange.converter.model;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyData {
    String table;
    String currency;
    String code;
    List<Rate> rates;
}
