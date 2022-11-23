package com.victorskg.cqrseventsourcingcore.domain;

public interface EventSourcingHandler<T> {

    void save(final T type);

    T getById(final String id);

    void republishEvents();

}
