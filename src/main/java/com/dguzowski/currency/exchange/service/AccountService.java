package com.dguzowski.currency.exchange.service;

import com.dguzowski.currency.exchange.converter.CurrencyConverter;
import com.dguzowski.currency.exchange.dto.AccountDto;
import com.dguzowski.currency.exchange.dto.CreateAccountRequestDto;
import com.dguzowski.currency.exchange.exception.InsufficientFundsException;
import com.dguzowski.currency.exchange.exception.ResourceNotFoundException;
import com.dguzowski.currency.exchange.helpers.CurrencyCode;
import com.dguzowski.currency.exchange.mapper.AccountMapper;
import com.dguzowski.currency.exchange.model.Account;
import com.dguzowski.currency.exchange.model.Currency;
import com.dguzowski.currency.exchange.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CurrencyConverter currencyConverter;

    public AccountDto saveAccount(UUID requestId, CreateAccountRequestDto createAccountRequest){
        return accountRepository.findAccountByRequestId(requestId)
                .map(AccountMapper::map)
                .orElseGet(() -> saveNewAccount(createAccountRequest, requestId));
    }

    private AccountDto saveNewAccount(CreateAccountRequestDto accountRequestDto, UUID requestId) {
        Account account = AccountMapper.map(accountRequestDto, requestId);
        accountRepository.save(account);
        return AccountMapper.map(account);
    }

    public AccountDto transferFunds(UUID id, BigDecimal amount, CurrencyCode code) {
        Account account = accountRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        Predicate<Currency> isEqualToCode = c -> c.getCodee().equals(code);

        Currency sourceCurrency = getCurrency(account, isEqualToCode);
        Currency targetCurrency = getCurrency(account, isEqualToCode.negate());

        BigDecimal accountAmount = sourceCurrency.getAmount();
        if (accountAmount.subtract(amount).signum() == -1) {
            throw new InsufficientFundsException(amount, accountAmount);
        }
        BigDecimal amountInTargetCurrency = currencyConverter.convert(amount, code);

        sourceCurrency.setAmount(sourceCurrency.getAmount().subtract(amount));
        targetCurrency.setAmount(targetCurrency.getAmount().add(amountInTargetCurrency));

        account = accountRepository.save(account);
        return AccountMapper.map(account);
    }

    private Currency getCurrency(Account account, Predicate<Currency> currencyPredicate) {
        return account.getCurrencies()
                .stream()
                .filter(currencyPredicate)
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
    }



}
