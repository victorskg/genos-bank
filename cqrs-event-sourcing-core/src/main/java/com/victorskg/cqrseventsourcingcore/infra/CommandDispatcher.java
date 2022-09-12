package com.victorskg.cqrseventsourcingcore.infra;

import com.victorskg.cqrseventsourcingcore.commands.AbstractCommand;
import com.victorskg.cqrseventsourcingcore.commands.CommandHandler;

public interface CommandDispatcher {

    <T extends AbstractCommand> void registerHandler(Class<T> type, CommandHandler<T> handler);

    <T extends AbstractCommand> void dispatch(T command);

}
