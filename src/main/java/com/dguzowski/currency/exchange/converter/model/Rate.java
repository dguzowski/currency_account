package com.dguzowski.currency.exchange.converter.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Rate {
  String no;
  LocalDate effectiveDate;
  BigDecimal ask;
  BigDecimal bid;
}
