package com.victorskg.genosbankaccountquery.infra.consumers;

import com.victorskg.genosbankaccountcommon.events.AccountClosedEvent;
import com.victorskg.genosbankaccountcommon.events.AccountOpenedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsDepositedEvent;
import com.victorskg.genosbankaccountcommon.events.FundsWithdrawnEvent;
import com.victorskg.genosbankaccountquery.infra.handlers.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Class to consume bank account events
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 14/09/2022
 */
@Component
@RequiredArgsConstructor
public class BankAccountEventConsumer implements EventConsumer {

    private final EventHandler bankAccountEventHandler;

    @Override
    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final AccountOpenedEvent event, final Acknowledgment acknowledgment) {
        bankAccountEventHandler.on(event);
        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final AccountClosedEvent event, final Acknowledgment acknowledgment) {
        bankAccountEventHandler.on(event);
        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final FundsDepositedEvent event, final Acknowledgment acknowledgment) {
        bankAccountEventHandler.on(event);
        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final FundsWithdrawnEvent event, final Acknowledgment acknowledgment) {
        bankAccountEventHandler.on(event);
        acknowledgment.acknowledge();
    }

}
