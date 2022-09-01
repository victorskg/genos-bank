package com.victorskg.cqrseventsourcingcore.infra;

import com.victorskg.cqrseventsourcingcore.events.AbstractEvent;

import java.util.List;

public interface EventStore {

    void saveEvents(final String aggregateId, final Iterable<AbstractEvent> events, final int expectedVersion);

    List<AbstractEvent> findAllByAggregateId(final String aggregateId);

}
