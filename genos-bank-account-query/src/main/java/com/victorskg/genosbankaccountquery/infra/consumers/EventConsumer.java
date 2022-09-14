package com.victorskg.genosbankaccountquery.infra.consumers;

import com.victorskg.genosbankaccountcommon.events.AccountClosedEvent;
import com.victorskg.genosbankaccountcommon.events.AccountOpenedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsDepositedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

    void consume(@Payload final AccountOpenedEvent event, final Acknowledgment acknowledgment);

    void consume(@Payload final AccountClosedEvent event, final Acknowledgment acknowledgment);

    void consume(@Payload final FundsDepositedEvent event, final Acknowledgment acknowledgment);

    void consume(@Payload final FundsWithdrawnEvent event, final Acknowledgment acknowledgment);

}
