package com.victorskg.genosbankaccountcommon.events;

import com.victorskg.cqrseventsourcingcore.events.AbstractEvent;
import lombok.Getter;

/**
 * Bank account closed event
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class AccountClosedEvent extends AbstractEvent {

    public AccountClosedEvent(final String id, final int version) {
        super(id, version);
    }

}
