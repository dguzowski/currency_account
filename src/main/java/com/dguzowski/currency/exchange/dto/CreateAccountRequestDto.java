package com.dguzowski.currency.exchange.dto;

import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Value
public class CreateAccountRequestDto {

    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @DecimalMin("0.00")
    @Digits(integer = 32, fraction = 2)
    BigDecimal initialAmount;
}
