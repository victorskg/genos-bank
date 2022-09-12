package com.victorskg.genosbankaccountcommand.infra;

import com.victorskg.cqrseventsourcingcore.infra.CommandDispatcher;
import com.victorskg.genosbankaccountcommand.application.commands.CloseAccountCommand;
import com.victorskg.genosbankaccountcommand.application.commands.CommandHandler;
import com.victorskg.genosbankaccountcommand.application.commands.DepositFundsCommand;
import com.victorskg.genosbankaccountcommand.application.commands.OpenAccountCommand;
import com.victorskg.genosbankaccountcommand.application.commands.WithdrawFundsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Account command configuration
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
@Configuration
@RequiredArgsConstructor
class AccountCommandConfiguration {

    private final CommandHandler accountCommandHandler;
    private final CommandDispatcher accountCommandDispatcher;

    @PostConstruct
    void registerHandlers() {
        accountCommandDispatcher.registerHandler(OpenAccountCommand.class, accountCommandHandler::handle);
        accountCommandDispatcher.registerHandler(CloseAccountCommand.class, accountCommandHandler::handle);
        accountCommandDispatcher.registerHandler(DepositFundsCommand.class, accountCommandHandler::handle);
        accountCommandDispatcher.registerHandler(WithdrawFundsCommand.class, accountCommandHandler::handle);
    }

}
