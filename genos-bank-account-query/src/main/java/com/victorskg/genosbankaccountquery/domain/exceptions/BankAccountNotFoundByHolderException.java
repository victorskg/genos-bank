package com.victorskg.genosbankaccountquery.domain.exceptions;

/**
 * Class that represents an account not found by holder exception
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 21/11/2022
 */
public class BankAccountNotFoundByHolderException extends RuntimeException {

    public BankAccountNotFoundByHolderException(final String holder) {
        super(String.format("Bank account for holder [%s] not found.", holder));
    }

}
