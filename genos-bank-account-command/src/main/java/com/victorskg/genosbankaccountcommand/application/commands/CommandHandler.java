package com.victorskg.genosbankaccountcommand.application.commands;

public interface CommandHandler {

    void handle(final OpenAccountCommand command);

    void handle(final CloseAccountCommand command);

    void handle(final WithdrawFundsCommand command);

    void handle(final DepositFundsCommand command);

}
