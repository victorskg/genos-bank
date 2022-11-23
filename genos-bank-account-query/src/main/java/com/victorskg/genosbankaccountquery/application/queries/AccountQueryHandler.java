package com.victorskg.genosbankaccountquery.application.queries;

import com.victorskg.genosbankaccountquery.domain.BankAccount;

import java.util.List;

/**
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
public interface AccountQueryHandler {
    List<BankAccount> handle(final FindAllAccounts query);
    List<BankAccount> handle(final FindAccountsById query);
    List<BankAccount> handle(final FindAccountByHolder query);
    List<BankAccount> handle(final FindAccountsWithBalance query);
}
