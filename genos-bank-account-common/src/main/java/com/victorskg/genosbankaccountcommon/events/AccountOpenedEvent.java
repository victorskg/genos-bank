package com.victorskg.genosbankaccountcommon.events;

import com.victorskg.cqrseventsourcingcore.events.AbstractEvent;
import com.victorskg.genosbankaccountcommon.domain.AccountType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Bank account opened event
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class AccountOpenedEvent extends AbstractEvent {

    private final String accountHolder;
    private final AccountType accountType;
    private final BigDecimal openingBalance;
    private final LocalDate creationDate;

    public AccountOpenedEvent(final String id, final int version, final String accountHolder, final AccountType accountType,
        final BigDecimal openingBalance, final LocalDate creationDate) {
        super(id, version);
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
        this.creationDate = creationDate;
    }

}
