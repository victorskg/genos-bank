package com.victorskg.cqrseventsourcingcore.infra;

import com.victorskg.cqrseventsourcingcore.domain.BaseEntity;
import com.victorskg.cqrseventsourcingcore.queries.BaseQuery;
import com.victorskg.cqrseventsourcingcore.queries.QueryHandler;

import java.util.List;

public interface QueryDispatcher<U extends BaseEntity> {

    <T extends BaseQuery> void registerHandler(final Class<T> type, final QueryHandler<T, U> handler);

    <T extends BaseQuery> List<U> send(final T query);

}
