package com.victorskg.genosbankaccountquery.infra.handlers;

import com.victorskg.genosbankaccountcommon.events.AccountClosedEvent;
import com.victorskg.genosbankaccountcommon.events.AccountOpenedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsDepositedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsWithdrawnEvent;

public interface EventHandler {

    void on(final AccountOpenedEvent event);

    void on(final AccountClosedEvent event);

    void on(final FundsDepositedEvent event);

    void on(final FundsWithdrawnEvent event);

}
