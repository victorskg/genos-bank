package com.victorskg.genosbankaccountcommon.events;

import com.victorskg.cqrseventsourcingcore.events.BaseEvent;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceCreator;

import java.math.BigDecimal;

/**
 * Funds deposit on a bank account event
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 22/08/2022
 */
@Getter
public class FundsWithdrawnEvent extends BaseEvent {

    private final BigDecimal amount;

    private FundsWithdrawnEvent() {
        this(null, null);
    }

    public FundsWithdrawnEvent(final String id, final BigDecimal amount) {
        super(id, 0);
        this.amount = amount;
    }

    @PersistenceCreator
    public FundsWithdrawnEvent(final String id, final int version, final BigDecimal amount) {
        super(id, version);
        this.amount = amount;
    }

}
