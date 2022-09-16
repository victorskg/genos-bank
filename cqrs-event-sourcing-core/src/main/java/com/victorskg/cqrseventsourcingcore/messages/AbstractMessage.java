package com.victorskg.cqrseventsourcingcore.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that represents an abstract message
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractMessage {

    protected String id;

}
