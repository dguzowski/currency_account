package com.dguzowski.currency.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(BigDecimal requestedAmount, BigDecimal accountAmount) {
        super("Account has insufficient amount. Requested: "+requestedAmount+", account funds: "+accountAmount);
    }
}
