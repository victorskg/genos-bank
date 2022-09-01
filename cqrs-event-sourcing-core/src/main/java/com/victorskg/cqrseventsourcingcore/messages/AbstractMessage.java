package com.victorskg.cqrseventsourcingcore.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Class that represents an abstract message
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractMessage {
    protected final String id;
}
