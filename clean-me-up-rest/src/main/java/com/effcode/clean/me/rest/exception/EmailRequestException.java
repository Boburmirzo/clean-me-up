package com.effcode.clean.me.rest.exception;

/**
 * To handle exception throughout the application
 */
public class EmailRequestException extends RuntimeException {

    public EmailRequestException(String message) {
        super(message);
    }
}
