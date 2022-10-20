package com.victorskg.genosbankaccountcommand.application.commands;

import com.victorskg.cqrseventsourcingcore.domain.EventSourcingHandler;
import com.victorskg.genosbankaccountcommand.domain.AccountAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for handling account commands
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
@Component
@RequiredArgsConstructor
public class AccountCommandHandler implements CommandHandler {

    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    @Transactional
    public void handle(final OpenAccountCommand command) {
        final var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    @Transactional
    public void handle(final CloseAccountCommand command) {
        final var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.close();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    @Transactional
    public void handle(final WithdrawFundsCommand command) {
        final var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.withdrawn(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    @Transactional
    public void handle(final DepositFundsCommand command) {
        final var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deposit(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

}
