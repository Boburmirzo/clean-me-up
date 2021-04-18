package com.effcode.clean.me.rest.validator;

import com.effcode.clean.me.rest.exception.EmailRequestException;
import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import com.effcode.clean.me.rest.service.EmailHandlerServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailHandlerServiceImpl.class);

    private static final String EMAIL_FILTER_PATTER = "^[(a-zA-Z-0-9-_+.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
    private static final int CONTENT_LENGTH_LIMIT = 3000;
    private static final int SUBJECT_LENGTH_LIMIT = 78;

    /**
     * Validate all the required parameters of request
     *
     * @param emailSendRequest email message to validate
     * @return true/false
     */
    public boolean validateEmailMessage(EmailSendRequest emailSendRequest) {

        if (Strings.isEmpty(emailSendRequest.getSubject().trim())
                || Strings.isEmpty(emailSendRequest.getToAddress().trim()) ||
                Strings.isEmpty(emailSendRequest.getContent().trim())) {

            LOGGER.error("Required parameter is null or empty" + emailSendRequest.getSubject() + "|" + emailSendRequest.getToAddress() + "|" + emailSendRequest.getContent());

            throw new EmailRequestException("Required parameter is null or empty");
        }
        if (!isValidEmailAddress(emailSendRequest.getToAddress())) {
            LOGGER.error("The email address " + emailSendRequest.getToAddress() + " is invalid.");

            throw new EmailRequestException("The email address  " + emailSendRequest.getToAddress() + " is invalid");
        }

        if (emailSendRequest.getSubject().length() > SUBJECT_LENGTH_LIMIT) {
            LOGGER.error("Subject must be below 78 characters: " + emailSendRequest.getSubject().length());

            throw new EmailRequestException("Subject must be below 78 characters.: " + emailSendRequest.getContent().length());
        }

        if (emailSendRequest.getContent().length() > CONTENT_LENGTH_LIMIT) {
            LOGGER.error("The email content length exceeded the limit: " + emailSendRequest.getContent().length());

            throw new EmailRequestException("The email content length exceeded the limit: " + emailSendRequest.getContent().length());
        }
        return true;
    }

    /**
     * Method to validate email Address with regex pattern
     *
     * @param emailAddress of email Ids with delimiter ";"
     * @return true/false
     */
    public boolean isValidEmailAddress(String emailAddress) {

        List<String> emailList = Collections.singletonList(emailAddress);

        Predicate<String> emailFilter = Pattern
                .compile(EMAIL_FILTER_PATTER)
                .asPredicate();

        return emailList
                .stream()
                .allMatch(emailFilter);
    }
}
