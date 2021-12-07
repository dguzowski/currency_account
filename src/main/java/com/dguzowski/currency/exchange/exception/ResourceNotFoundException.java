package com.dguzowski.currency.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource with provided id does not exist")
public class ResourceNotFoundException extends RuntimeException{
}
