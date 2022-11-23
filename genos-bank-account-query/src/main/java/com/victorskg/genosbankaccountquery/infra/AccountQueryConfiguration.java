package com.victorskg.genosbankaccountquery.infra;

import com.victorskg.cqrseventsourcingcore.infra.QueryDispatcher;
import com.victorskg.genosbankaccountquery.application.queries.AccountQueryHandler;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountByHolder;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsById;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsWithBalance;
import com.victorskg.genosbankaccountquery.application.queries.FindAllAccounts;
import com.victorskg.genosbankaccountquery.domain.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Account query configuration
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@Configuration
@RequiredArgsConstructor
public class AccountQueryConfiguration {

    private final QueryDispatcher<BankAccount> accountQueryDispatcher;
    private final AccountQueryHandler bankAccountQueryHandler;

    @PostConstruct
    void registerHandlers() {
        accountQueryDispatcher.registerHandler(FindAccountByHolder.class, bankAccountQueryHandler::handle);
        accountQueryDispatcher.registerHandler(FindAccountsById.class, bankAccountQueryHandler::handle);
        accountQueryDispatcher.registerHandler(FindAccountsWithBalance.class, bankAccountQueryHandler::handle);
        accountQueryDispatcher.registerHandler(FindAllAccounts.class, bankAccountQueryHandler::handle);
    }

}
