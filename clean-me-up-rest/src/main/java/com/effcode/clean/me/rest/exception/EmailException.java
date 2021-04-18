package com.effcode.clean.me.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * EmailException class to handle exception
 */
@Data
@AllArgsConstructor
public class EmailException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

}
