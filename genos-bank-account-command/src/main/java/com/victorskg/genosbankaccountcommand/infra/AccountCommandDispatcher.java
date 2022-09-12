package com.victorskg.genosbankaccountcommand.infra;

import com.victorskg.cqrseventsourcingcore.commands.AbstractCommand;
import com.victorskg.cqrseventsourcingcore.commands.CommandHandler;
import com.victorskg.cqrseventsourcingcore.infra.CommandDispatcher;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * Class responsible for register handlers and dispatch account commands
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Component
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends AbstractCommand>, CommandHandler> commandHandlerStrategy = new HashMap<>();

    @Override
    public <T extends AbstractCommand> void registerHandler(final Class<T> type, final CommandHandler<T> handler) {
        commandHandlerStrategy.putIfAbsent(type, handler);
    }

    @Override
    public <T extends AbstractCommand> void dispatch(final T command) {
        final var handler = commandHandlerStrategy.get(command.getClass());
        if (isNull(handler)) throw new CommandHandlerNotRegisteredException(command.getClass().getName());
        handler.handle(command);
    }

}
