package com.dguzowski.currency.exchange.controller;

import com.dguzowski.currency.exchange.dto.AccountDto;
import com.dguzowski.currency.exchange.dto.CreateAccountRequestDto;
import com.dguzowski.currency.exchange.dto.CurrencyTransferDto;
import com.dguzowski.currency.exchange.exception.ResourceNotFoundException;
import com.dguzowski.currency.exchange.mapper.AccountMapper;
import com.dguzowski.currency.exchange.repository.AccountRepository;
import com.dguzowski.currency.exchange.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccount(@PathVariable UUID id) {
        return accountRepository.findById(id)
                .map(AccountMapper::map)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDto createAccount(@RequestHeader("Request-Id") UUID requestId, @Valid @RequestBody CreateAccountRequestDto accountRequestDto) {
        return accountService.saveAccount(requestId, accountRequestDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto exchangeCurrency(@PathVariable UUID id, @Valid @RequestBody CurrencyTransferDto transferDto) {
        return accountService.transferFunds(id, transferDto.getAmount(), transferDto.getCode());
    }

}
