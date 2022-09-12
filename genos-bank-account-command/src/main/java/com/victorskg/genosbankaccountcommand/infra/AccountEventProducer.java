package com.victorskg.genosbankaccountcommand.infra;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import com.victorskg.cqrseventsourcingcore.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Class that produces account events to a topic
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
@Component
@RequiredArgsConstructor
public class AccountEventProducer implements EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(final String topic, final BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }

}
