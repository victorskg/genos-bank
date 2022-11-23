package com.victorskg.genosbankaccountquery.infra.handlers;

import com.victorskg.genosbankaccountcommon.events.AccountClosedEvent;
import com.victorskg.genosbankaccountcommon.events.AccountOpenedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsDepositedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsWithdrawnEvent;
import com.victorskg.genosbankaccountquery.domain.BankAccount;
import com.victorskg.genosbankaccountquery.domain.BankAccountRepository;
import com.victorskg.genosbankaccountquery.domain.exceptions.BankAccountNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Bank account event handler
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 13/09/2022
 */
@Component
@RequiredArgsConstructor
public class BankAccountEventHandler implements EventHandler {

    private final BankAccountRepository repository;

    @Override
    public void on(final AccountOpenedEvent event) {
        final var bankAccount = BankAccount.builder()
            .id(event.getId())
            .accountHolder(event.getAccountHolder())
            .type(event.getAccountType())
            .createdAt(LocalDateTime.now())
            .balance(event.getOpeningBalance())
            .build();
        repository.save(bankAccount);
    }

    @Override
    public void on(final AccountClosedEvent event) {
        final var bankAccount = findById(event.getId());
        repository.delete(bankAccount);
    }

    @Override
    @Transactional
    public void on(final FundsDepositedEvent event) {
        final var bankAccount = findById(event.getId());
        bankAccount.deposit(event.getAmount());
    }

    @Override
    @Transactional
    public void on(final FundsWithdrawnEvent event) {
        final var bankAccount = findById(event.getId());
        bankAccount.withdrawn(event.getAmount());
    }

    private BankAccount findById(final String id) {
        return repository.findById(id).orElseThrow(() -> new BankAccountNotFoundByIdException(id));
    }

}
