package com.victorskg.genosbankaccountquery.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountHolder(final String accountHolder);

    List<BankAccount> findAllByBalanceGreaterThan(final BigDecimal balance);

    List<BankAccount> findAllByBalanceLessThan(final BigDecimal balance);

}
