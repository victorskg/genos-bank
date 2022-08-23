package com.victorskg.genosbankaccountcommand.infra;

/**
 * Exception for command handler not registered
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
public class CommandHandlerNotRegisteredException extends RuntimeException {
    public CommandHandlerNotRegisteredException(final String command) {
        super(String.format(String.format("No handler registered for command [%s]", command)));
    }
}
