package com.victorskg.genosbankaccountquery.application.controllers;

import com.victorskg.cqrseventsourcingcore.infra.QueryDispatcher;
import com.victorskg.genosbankaccountquery.application.dto.EqualityType;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountByHolder;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsById;
import com.victorskg.genosbankaccountquery.application.queries.FindAccountsWithBalance;
import com.victorskg.genosbankaccountquery.application.queries.FindAllAccounts;
import com.victorskg.genosbankaccountquery.domain.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Bank account query controller
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bank-account-query")
public class BankAccountQueryController {

    private final QueryDispatcher<BankAccount> accountQueryDispatcher;

    @GetMapping
    public List<BankAccount> findAll() {
        return accountQueryDispatcher.send(new FindAllAccounts());
    }

    @GetMapping("{id}")
    public List<BankAccount> findById(@PathVariable("id") final String id) {
        return accountQueryDispatcher.send(new FindAccountsById(id));
    }

    @GetMapping("/holder/{holder}")
    public List<BankAccount> findByHolder(@PathVariable("holder") final String holder) {
        return accountQueryDispatcher.send(new FindAccountByHolder(holder));
    }

    @GetMapping("/balance/greater")
    public List<BankAccount> findByBalanceGreaterThan(@RequestParam("balance") final BigDecimal balance) {
        return accountQueryDispatcher.send(new FindAccountsWithBalance(EqualityType.GREATER_THAN, balance));
    }

    @GetMapping("/balance/lesser")
    public List<BankAccount> findByBalanceLessThan(@RequestParam("balance") final BigDecimal balance) {
        return accountQueryDispatcher.send(new FindAccountsWithBalance(EqualityType.LESS_THAN, balance));
    }

}
