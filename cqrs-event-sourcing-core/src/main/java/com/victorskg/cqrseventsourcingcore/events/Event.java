package com.victorskg.cqrseventsourcingcore.events;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Event model
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 01/09/2022
 */
@Data
@Builder
@Document(collection = "eventStore")
public class Event {

    @Id
    private String id;

    private LocalDateTime createdAt;

    private String aggregateId;

    private String aggregateType;

    private int version;

    private String type;

    private AbstractEvent data;

}
