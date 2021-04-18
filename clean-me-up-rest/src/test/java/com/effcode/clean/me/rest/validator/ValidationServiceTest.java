package com.effcode.clean.me.rest.validator;

import com.effcode.clean.me.rest.exception.EmailRequestException;
import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private EmailValidator emailValidator;

    @Test
    void validateEmailWithEmptyEmailAddress() {
        EmailSendRequest email = new EmailSendRequest("", "email subject", "lite content");

        Exception exception = assertThrows(EmailRequestException.class, () -> {
            emailValidator.validateEmailMessage(email);
        });

        String expectedMessage = "Required parameter is null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmailWithInvalidEmailAddress() {
        EmailSendRequest email = new EmailSendRequest("lala.test", "email subject", "lite content");

        Exception exception = assertThrows(EmailRequestException.class, () -> {
            emailValidator.validateEmailMessage(email);
        });

        String expectedMessage = "is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmailWithLongSubject() {
        String longSubject = StringUtils.repeat("A", 79);

        EmailSendRequest email = new EmailSendRequest("lala@test.se", longSubject, "lite content");

        Exception exception = assertThrows(EmailRequestException.class, () -> {
            emailValidator.validateEmailMessage(email);
        });

        String expectedMessage = "Subject must be below 78 characters.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmail() throws EmailRequestException {
        EmailSendRequest email = new EmailSendRequest("lala@test.se", "hej", "lite content");

        emailValidator.validateEmailMessage(email);
    }

}