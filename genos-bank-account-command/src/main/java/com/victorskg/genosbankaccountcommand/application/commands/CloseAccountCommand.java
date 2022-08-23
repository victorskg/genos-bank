package com.victorskg.genosbankaccountcommand.application.commands;

import com.victorskg.cqrseventsourcingcore.commands.AbstractCommand;

/**
 * Command to close a bank account
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
public class CloseAccountCommand extends AbstractCommand {

    protected CloseAccountCommand(final String id) {
        super(id);
    }

}
