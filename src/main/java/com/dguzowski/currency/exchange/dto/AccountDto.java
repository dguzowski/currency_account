package com.dguzowski.currency.exchange.dto;

import com.dguzowski.currency.exchange.model.Currency;
import lombok.Value;

import java.util.Collection;
import java.util.UUID;

@Value
public class AccountDto {
    UUID id;
    String firstName;
    String lastName;
    Collection<Currency> currencies;
}
