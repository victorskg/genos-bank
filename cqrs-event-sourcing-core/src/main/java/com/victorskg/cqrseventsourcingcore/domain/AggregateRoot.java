package com.victorskg.cqrseventsourcingcore.domain;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Aggregate root
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
public abstract class AggregateRoot {

    @Getter
    protected String id;

    @Getter
    @Setter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public List<BaseEvent> getUncommittedChanges() {
        return changes;
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }

    protected void applyChanges(final BaseEvent event, final boolean isNewEvent) {
        apply(event);
        if (isNewEvent) changes.add(event);
    }

    protected abstract void apply(final BaseEvent event);

    public void raiseEvent(final BaseEvent event) {
        applyChanges(event, true);
    }

    public void replyEvents(final Iterable<BaseEvent> events) {
        events.forEach(event -> applyChanges(event, false));
    }

}
