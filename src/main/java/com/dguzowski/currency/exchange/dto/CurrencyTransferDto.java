package com.dguzowski.currency.exchange.dto;

import com.dguzowski.currency.exchange.helpers.CurrencyCode;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class CurrencyTransferDto {
    @DecimalMin("0.00")
    @Digits(integer = 32, fraction = 2)
    BigDecimal amount;
    @NotNull
    CurrencyCode code;
}
