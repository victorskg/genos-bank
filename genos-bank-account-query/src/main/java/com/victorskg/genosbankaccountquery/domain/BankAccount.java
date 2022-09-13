package com.victorskg.genosbankaccountquery.domain;

import com.victorskg.cqrseventsourcingcore.domain.BaseEntity;
import com.victorskg.genosbankaccountcommon.domain.AccountType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Bank account entity
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 13/09/2022
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class BankAccount extends BaseEntity {

    @Id
    private String id;

    private String accountHolder;

    private LocalDateTime createdAt;

    private AccountType type;

    private BigDecimal balance;

}
