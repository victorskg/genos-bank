package com.victorskg.cqrseventsourcingcore.events;

import com.victorskg.cqrseventsourcingcore.messages.AbstractMessage;
import lombok.Getter;

/**
 * Class that represents an abstract event
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class AbstractEvent extends AbstractMessage {

    protected final int version;

    public AbstractEvent(final String id, final int version) {
        super(id);
        this.version = version;
    }

}
