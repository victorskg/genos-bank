package com.victorskg.genosbankaccountquery.application.queries;

import com.victorskg.cqrseventsourcingcore.queries.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindAccountsById extends BaseQuery {

    private final String id;

}
