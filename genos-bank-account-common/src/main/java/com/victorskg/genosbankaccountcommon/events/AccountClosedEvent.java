package com.victorskg.genosbankaccountcommon.events;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import lombok.Getter;

/**
 * Bank account closed event
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class AccountClosedEvent extends BaseEvent {

    private AccountClosedEvent() {
        this(null);
    }

    public AccountClosedEvent(final String id) {
        super(id, 0);
    }

    public AccountClosedEvent(final String id, final int version) {
        super(id, version);
    }

}
