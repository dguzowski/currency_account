package com.dguzowski.currency.exchange.model;

import com.dguzowski.currency.exchange.helpers.CurrencyCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Currency {

    @DecimalMin("0.00")
    @Digits(integer = 32, fraction = 2)
    @Column(nullable = false)
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CurrencyCode codee;
}