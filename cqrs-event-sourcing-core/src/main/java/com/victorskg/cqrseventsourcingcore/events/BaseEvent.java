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
public class BaseEvent extends AbstractMessage {

    protected int version;

    public BaseEvent(final String id, final int version) {
        super(id);
        this.version = version;
    }

    public BaseEvent withVersion(final int version) {
        this.version = version;
        return this;
    }

}
