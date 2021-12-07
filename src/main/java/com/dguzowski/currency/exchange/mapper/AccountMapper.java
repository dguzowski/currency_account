package com.dguzowski.currency.exchange.mapper;

import com.dguzowski.currency.exchange.dto.AccountDto;
import com.dguzowski.currency.exchange.dto.CreateAccountRequestDto;
import com.dguzowski.currency.exchange.helpers.CurrencyCode;
import com.dguzowski.currency.exchange.model.Account;
import com.dguzowski.currency.exchange.model.Currency;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class AccountMapper {
    private AccountMapper() {
    }

    public static Account map(CreateAccountRequestDto accountRequestDto, UUID requestId) {
        Set<Currency> currencies = new HashSet<>();
        currencies.add(new Currency(accountRequestDto.getInitialAmount(), CurrencyCode.PLN));
        currencies.add(new Currency(BigDecimal.ZERO, CurrencyCode.USD));
        Account account = new Account();
        account.setFirstName(accountRequestDto.getFirstName());
        account.setLastName(accountRequestDto.getLastName());
        account.setRequestId(requestId);
        account.setCurrencies(currencies);
        return account;
    }

    public static AccountDto map(Account account) {
        return new AccountDto(account.getId(), account.getFirstName(), account.getLastName(), account.getCurrencies());
    }
}
