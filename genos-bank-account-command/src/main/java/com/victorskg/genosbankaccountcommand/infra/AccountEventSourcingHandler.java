package com.victorskg.genosbankaccountcommand.infra;

import com.victorskg.cqrseventsourcingcore.domain.EventSourcingHandler;
import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import com.victorskg.cqrseventsourcingcore.infra.EventStore;
import com.victorskg.genosbankaccountcommand.domain.AccountAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Account aggregate event sourcing handler
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
@Service
@RequiredArgsConstructor
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    @Override
    public void save(final AccountAggregate aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(final String id) {
        final var aggregate = new AccountAggregate();
        final var events = eventStore.findAllByAggregateId(id);

        if (nonNull(events) && !events.isEmpty()) {
            replyEventsOnAggregate(events, aggregate);
        }

        return aggregate;
    }

    private void replyEventsOnAggregate(final List<BaseEvent> events, final AccountAggregate aggregate) {
        aggregate.replyEvents(events);
        final var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder()).orElse(0);
        aggregate.setVersion(latestVersion);
    }

}
