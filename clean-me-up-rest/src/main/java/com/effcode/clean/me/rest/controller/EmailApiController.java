package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.exception.EmailRequestException;
import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import com.effcode.clean.me.rest.service.EmailHandlerService;
import com.effcode.clean.me.rest.validator.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * EmailApiController - RESTful API endpoint for sending email
 */
@RestController
@RequestMapping("/api")
public class EmailApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailApiController.class);

    private final EmailHandlerService emailHandler;
    private final EmailValidator emailValidator;

    public EmailApiController(EmailHandlerService emailHandler,
                              EmailValidator emailValidator) {
        this.emailHandler = emailHandler;
        this.emailValidator = emailValidator;
    }

    /**
     * @param email EmailSendRequest sends email with JSON request - toAddress, subject & content
     * @return the message if email sent successfully
     * @throws EmailRequestException email validation exception
     */
    @PostMapping(value = "/send", consumes = "application/json")
    @PreAuthorize("hasRole('MAIL_SENDER')")
    public ResponseEntity<Void> send(@RequestBody EmailSendRequest email) {

        LOGGER.info("Processing email send requests.");

        try {

            emailValidator.validateEmailMessage(email);

        } catch (EmailRequestException e) {
            LOGGER.error("Invalid email data provided: {}", e.getMessage());
            MDC.clear();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Sending email.");

        emailHandler.send(email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
