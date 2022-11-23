package com.victorskg.genosbankaccountquery.infra.handlers;

import com.victorskg.genosbankaccountquery.application.queries.AccountQueryHandler;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountByHolder;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsById;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsWithBalance;
import com.victorskg.genosbankaccountquery.application.queries.FindAllAccounts;
import com.victorskg.genosbankaccountquery.domain.BankAccount;
import com.victorskg.genosbankaccountquery.domain.BankAccountRepository;
import com.victorskg.genosbankaccountquery.domain.exceptions.BankAccountNotFoundByHolderException;
import com.victorskg.genosbankaccountquery.domain.exceptions.BankAccountNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@Component
@RequiredArgsConstructor
public class BankAccountQueryHandler implements AccountQueryHandler {

    private final BankAccountRepository repository;

    @Override
    public List<BankAccount> handle(final FindAllAccounts query) {
        return repository.findAll();
    }

    @Override
    public List<BankAccount> handle(final FindAccountsById query) {
        return repository.findById(query.getId())
            .map(List::of)
            .orElseThrow(() -> new BankAccountNotFoundByIdException(query.getId()));
    }

    @Override
    public List<BankAccount> handle(final FindAccountByHolder query) {
        return repository.findByAccountHolder(query.getAccountHolder())
            .map(List::of)
            .orElseThrow(() -> new BankAccountNotFoundByHolderException(query.getAccountHolder()));
    }

    @Override
    public List<BankAccount> handle(final FindAccountsWithBalance query) {
        return switch (query.getEqualityType()) {
            case LESS_THAN -> repository.findAllByBalanceLessThan(query.getBalance());
            case GREATER_THAN -> repository.findAllByBalanceGreaterThan(query.getBalance());
        };
    }

}
