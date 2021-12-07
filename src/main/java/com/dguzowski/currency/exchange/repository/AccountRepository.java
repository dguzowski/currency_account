package com.dguzowski.currency.exchange.repository;

import com.dguzowski.currency.exchange.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByRequestId(UUID requestId);
}
