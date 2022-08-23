package com.victorskg.genosbankaccountcommand.application.commands;

import com.victorskg.cqrseventsourcingcore.commands.AbstractCommand;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Command to deposit funds on a bank account
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class DepositFundsCommand extends AbstractCommand {

    private final BigDecimal amount;

    public DepositFundsCommand(final String id, final BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

}
