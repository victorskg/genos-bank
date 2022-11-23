package com.victorskg.cqrseventsourcingcore.infra;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void saveEvents(final String aggregateId, final Iterable<BaseEvent> events, final int expectedVersion);

    List<BaseEvent> findAllByAggregateId(final String aggregateId);

    List<String> getAggregateIds();

}
