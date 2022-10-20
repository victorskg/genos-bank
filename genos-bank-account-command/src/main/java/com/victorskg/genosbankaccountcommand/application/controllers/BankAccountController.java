package com.victorskg.genosbankaccountcommand.application.controllers;

import com.victorskg.cqrseventsourcingcore.infra.CommandDispatcher;
import com.victorskg.genosbankaccountcommand.application.commands.CloseAccountCommand;
import com.victorskg.genosbankaccountcommand.application.commands.DepositFundsCommand;
import com.victorskg.genosbankaccountcommand.application.commands.OpenAccountCommand;
import com.victorskg.genosbankaccountcommand.application.commands.WithdrawFundsCommand;
import com.victorskg.genosbankaccountcommand.application.controllers.dto.BankAccountOpenedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Bank account REST controller
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 14/09/2022
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bank-account")
public class BankAccountController {

    private final CommandDispatcher accountCommandDispatcher;

    @PostMapping
    @ResponseStatus(CREATED)
    public BankAccountOpenedResponse open(@RequestBody final OpenAccountCommand openAccountCommand) {
        final var uuid = UUID.randomUUID().toString();
        openAccountCommand.setId(uuid);
        accountCommandDispatcher.dispatch(openAccountCommand);
        return BankAccountOpenedResponse.of(uuid);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping("{id}/deposit")
    public void depositFunds(@PathVariable("id") final String id, @RequestBody final DepositFundsCommand depositFundsCommand) {
        depositFundsCommand.setId(id);
        accountCommandDispatcher.dispatch(depositFundsCommand);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping("{id}/withdrawn")
    public void withdrawnFunds(@PathVariable("id") final String id, @RequestBody final WithdrawFundsCommand withdrawFundsCommand) {
        withdrawFundsCommand.setId(id);
        accountCommandDispatcher.dispatch(withdrawFundsCommand);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void close(@PathVariable("id") final String id) {
        accountCommandDispatcher.dispatch(new CloseAccountCommand(id));
    }

}
