package com.victorskg.genosbankaccountquery.application.queries;

import com.victorskg.cqrseventsourcingcore.queries.BaseQuery;
import com.victorskg.genosbankaccountquery.application.dto.EqualityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindAccountsWithBalance extends BaseQuery {

    private final EqualityType equalityType;
    private final BigDecimal balance;

}
