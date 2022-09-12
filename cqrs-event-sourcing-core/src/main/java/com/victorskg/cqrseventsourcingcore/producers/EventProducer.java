package com.victorskg.cqrseventsourcingcore.producers;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;

public interface EventProducer {

    void produce(final String topic, final BaseEvent event);

}
