package com.victorskg.genosbankaccountcommand.application.commands;

import com.victorskg.cqrseventsourcingcore.commands.AbstractCommand;
import com.victorskg.genosbankaccountcommon.domain.AccountType;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Command to open bank account
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class OpenAccountCommand extends AbstractCommand {

    private final String accountHolder;
    private final AccountType accountType;
    private final BigDecimal openingBalance;

    public OpenAccountCommand(final String id, final String accountHolder, final AccountType accountType, final BigDecimal openingBalance) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
    }

}
