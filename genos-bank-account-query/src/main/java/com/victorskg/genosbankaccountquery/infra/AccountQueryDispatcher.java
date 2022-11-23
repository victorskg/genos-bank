package com.victorskg.genosbankaccountquery.infra;

import com.victorskg.cqrseventsourcingcore.infra.QueryDispatcher;
import com.victorskg.cqrseventsourcingcore.queries.BaseQuery;
import com.victorskg.cqrseventsourcingcore.queries.QueryHandler;
import com.victorskg.genosbankaccountquery.domain.BankAccount;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@Component
public class AccountQueryDispatcher implements QueryDispatcher<BankAccount> {

    private final Map<Class<? extends BaseQuery>, List<QueryHandler>> queryStrategyHandler = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(final Class<T> type, final QueryHandler<T, BankAccount> handler) {
        final var handlers = queryStrategyHandler.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    public <T extends BaseQuery> List<BankAccount> send(final T query) {
        final var handlers = queryStrategyHandler.get(query.getClass());
        return Optional.ofNullable(handlers)
            .filter(queryHandlers -> queryHandlers.size() == 1)
            .map(queryHandlers -> queryHandlers.get(0).handle(query))
            .orElseThrow(() -> new RuntimeException("Impossible to handle the given query. Make sure that only one handler is registered."));
    }

}
