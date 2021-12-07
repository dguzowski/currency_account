package com.dguzowski.currency.exchange.converter;

import com.dguzowski.currency.exchange.converter.exception.CannotConvertMoneyException;
import com.dguzowski.currency.exchange.converter.model.CurrencyData;
import com.dguzowski.currency.exchange.helpers.CurrencyCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
public class NBPCurrentCurrencyConverter implements CurrencyConverter {

    private static CurrencyData dollarLastData;
    private CurrencyDataProvider currencyDataProvider;
    private CurrencyDataValidator currencyDataValidator;

    @Override
    public BigDecimal convert(BigDecimal amount, CurrencyCode from) {
        if (from == CurrencyCode.PLN) {
            return convertFrom(amount);
        }
        return convertTo(amount);
    }

    private BigDecimal convertFrom(BigDecimal amount) {
        checkLastDollarData();
        BigDecimal dollarLastAsk = dollarLastData
                .getRates().get(0)
                .getAsk();
        return amount.divide(dollarLastAsk, 2, RoundingMode.FLOOR);
    }

    private BigDecimal convertTo(BigDecimal amount) {
        checkLastDollarData();
        BigDecimal dollarLastAsk = dollarLastData
                .getRates().get(0)
                .getBid();
        return amount.multiply(dollarLastAsk).setScale(2, RoundingMode.FLOOR);
    }

    private void checkLastDollarData() {
        if (!currencyDataValidator.isCurrencyDataValid(dollarLastData) || !currencyDataValidator.isCurrencyDataUpToDate(dollarLastData)) {
            dollarLastData = currencyDataProvider.getCurrentDollarData()
                    .orElse(dollarLastData);
        }
        if (!currencyDataValidator.isCurrencyDataValid(dollarLastData)) {
            throw new CannotConvertMoneyException();
        }
    }
}
