package com.victorskg.genosbankaccountcommand.infra;

import com.victorskg.cqrseventsourcingcore.domain.exceptions.ConcurrencyException;
import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import com.victorskg.cqrseventsourcingcore.events.Event;
import com.victorskg.cqrseventsourcingcore.infra.EventStore;
import com.victorskg.cqrseventsourcingcore.producers.EventProducer;
import com.victorskg.genosbankaccountcommand.domain.AccountAggregate;
import com.victorskg.genosbankaccountcommand.domain.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Account event store
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
@Service
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventProducer accountEventProducer;
    private final EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(final String aggregateId, final Iterable<BaseEvent> events, final int expectedVersion) {
        final var currentEvents = eventStoreRepository.findAllByAggregateId(aggregateId);
        final var currentVersion = new AtomicInteger(extractCurrentVersion(currentEvents, expectedVersion));
        events.forEach(event -> saveEvent(aggregateId, currentVersion.incrementAndGet(), event));
    }

    @Override
    public List<BaseEvent> findAllByAggregateId(final String aggregateId) {
        var eventStream = eventStoreRepository.findAllByAggregateId(aggregateId);
        return eventStream.stream().map(Event::getData).toList();
    }

    @Override
    public List<String> getAggregateIds() {
        return eventStoreRepository.findAll()
            .stream()
            .map(Event::getAggregateId)
            .distinct()
            .toList();
    }

    private void saveEvent(final String aggregateId, final int currentVersion, final BaseEvent event) {
        final var eventModel = Event.builder()
            .createdAt(LocalDateTime.now())
            .aggregateId(aggregateId)
            .aggregateType(AccountAggregate.class.getTypeName())
            .version(currentVersion)
            .type(event.getClass().getTypeName())
            .data(event.withVersion(currentVersion))
            .build();
        eventStoreRepository.save(eventModel);
        accountEventProducer.produce(event.getClass().getSimpleName(), event);
    }

    private int extractCurrentVersion(final List<Event> currentEvents, final int expectedVersion) {
        if (nonNull(currentEvents) && !currentEvents.isEmpty()) {
            final var lastEventIndex = currentEvents.size() - 1;
            final var currentVersion = currentEvents.get(lastEventIndex).getVersion();
            if (currentVersion != expectedVersion) throw new ConcurrencyException(expectedVersion);
        }
        return expectedVersion;
    }

}
