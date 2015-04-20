package com.lmn.speed.valetservice.exception;

import com.lmn.speed.valetservice.ValetingQuoteService;

/**
 * Created by lsouza on 16/04/2015.
 */
public class ValetingQuoteException extends RuntimeException {

    private String message;

    public ValetingQuoteException(Exception ex) {
        super(ex);
    }

    public ValetingQuoteException(String message, Exception ex) {
        super(message, ex);
    }

}
