package com.victorskg.cqrseventsourcingcore.queries;

import com.victorskg.cqrseventsourcingcore.domain.BaseEntity;

import java.util.List;

/**
 * Class comments go here...
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@FunctionalInterface
public interface QueryHandler<T extends BaseQuery, U extends BaseEntity> {

    List<U> handle(final T query);

}
