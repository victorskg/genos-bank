package com.victorskg.genosbankaccountcommand.domain;

import com.victorskg.cqrseventsourcingcore.events.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<Event, String> {

    List<Event> findAllByAggregateId(final String aggregateId);

}
