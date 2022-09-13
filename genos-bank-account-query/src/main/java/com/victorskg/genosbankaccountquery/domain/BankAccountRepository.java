package com.victorskg.genosbankaccountquery.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountHolder(final String accountHolder);

    List<BankAccount> findAllByBalanceGreaterThan(final BigDecimal balance);

    List<BankAccount> findAllByBalanceLessThan(final BigDecimal balance);

}
