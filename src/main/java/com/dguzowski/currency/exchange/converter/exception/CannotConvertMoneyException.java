package com.dguzowski.currency.exchange.converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot get NBP Data")
public class CannotConvertMoneyException extends RuntimeException {
}
