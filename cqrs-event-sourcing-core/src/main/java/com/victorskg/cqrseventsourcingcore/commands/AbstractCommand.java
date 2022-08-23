package com.victorskg.cqrseventsourcingcore.commands;

import com.victorskg.cqrseventsourcingcore.messages.AbstractMessage;

/**
 * Class that represents an abstract command
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
public abstract class AbstractCommand extends AbstractMessage {

    protected AbstractCommand(final String id) {
        super(id);
    }

}
