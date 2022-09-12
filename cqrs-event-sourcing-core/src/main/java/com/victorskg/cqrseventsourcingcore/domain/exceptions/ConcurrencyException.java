package com.victorskg.cqrseventsourcingcore.domain.exceptions;

/**
 * Concurrency Exception
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 12/09/2022
 */
public class ConcurrencyException extends RuntimeException {

    public ConcurrencyException(final int version) {
        super(String.format("The given operation is out of version %d.", version));
    }

}
