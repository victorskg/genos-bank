package com.victorskg.genosbankaccountcommand.application.controllers.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Bank account opened response
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 14/09/2022
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class BankAccountOpenedResponse {

    private final String id;

}
