package com.effcode.clean.me.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class EmailExceptionHandler {

    @ExceptionHandler(value = {EmailRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(EmailRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        EmailException apiException = new EmailException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, badRequest);
    }
}
