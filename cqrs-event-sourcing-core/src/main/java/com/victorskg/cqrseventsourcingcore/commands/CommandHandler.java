package com.victorskg.cqrseventsourcingcore.commands;

@FunctionalInterface
public interface CommandHandler<T extends AbstractCommand> {

    void handle(final AbstractCommand command);

}
