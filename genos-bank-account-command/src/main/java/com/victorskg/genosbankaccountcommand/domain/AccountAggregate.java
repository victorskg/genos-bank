package com.victorskg.genosbankaccountcommand.domain;

import com.victorskg.cqrseventsourcingcore.domain.AggregateRoot;
import com.victorskg.cqrseventsourcingcore.events.AbstractEvent;
import com.victorskg.genosbankaccountcommand.application.commands.OpenAccountCommand;
import com.victorskg.genosbankaccountcommon.events.AccountClosedEvent;
import com.victorskg.genosbankaccountcommon.events.AccountOpenedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsDepositedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Account Aggregate
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 31/08/2022
 */
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private boolean active;

    private BigDecimal balance;

    public AccountAggregate(final OpenAccountCommand command) {
        raiseEvent(new AccountOpenedEvent(command.getId(), command.getAccountHolder(), command.getAccountType(), command.getOpeningBalance()));
    }

    public void close() {
        verifyIfIsValidToOperate();
        raiseEvent(new AccountClosedEvent(id));
    }

    public void deposit(final BigDecimal amount) {
        verifyIfIsValidToOperate();
        verifyIfAmountIsValid(amount);
        raiseEvent(new FundsDepositedEvent(id, amount));
    }

    public void withdrawn(final BigDecimal amount) {
        verifyIfIsValidToOperate();
        verifyIfAmountIsValid(amount);
        raiseEvent(new FundsWithdrawnEvent(id, amount));
    }

    @Override
    protected void apply(final AbstractEvent event) {
        switch (event) {
            case AccountOpenedEvent accountOpenedEvent -> apply(accountOpenedEvent);
            case AccountClosedEvent accountClosedEvent -> apply(accountClosedEvent);
            case FundsDepositedEvent fundsDepositedEvent -> apply(fundsDepositedEvent);
            case FundsWithdrawnEvent fundsWithdrawnEvent -> apply(fundsWithdrawnEvent);
            default -> throw new IllegalArgumentException(String.format("Unknown event type [%s]", event.getClass()));
        }
    }

    private void apply(final AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    private void apply(final AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }

    private void apply(final FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance = this.balance.add(event.getAmount());
    }

    private void apply(final FundsWithdrawnEvent event) {
        this.id = event.getId();
        this.balance = this.balance.subtract(event.getAmount());
    }

    private void verifyIfIsValidToOperate() {
        if (!active) {
            throw new IllegalStateException("Impossible to operate in a closed account.");
        }
    }

    private void verifyIfAmountIsValid(final BigDecimal amount) {
        if (amount.intValue() <= 0) {
            throw new IllegalArgumentException(String.format("Amount value %s should be greater than 0.", amount));
        }
    }

}
