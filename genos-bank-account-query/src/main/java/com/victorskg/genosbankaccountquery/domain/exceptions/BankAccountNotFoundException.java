package com.victorskg.genosbankaccountquery.domain.exceptions;

/**
 * Class that represents an account not found exception
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 13/09/2022
 */
public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(final String id) {
        super(String.format("Bank account with id [%s] not found.", id));
    }

}
