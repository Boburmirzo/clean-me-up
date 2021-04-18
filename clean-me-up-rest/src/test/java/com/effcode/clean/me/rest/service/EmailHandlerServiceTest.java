package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.config.SmtpConfig;
import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailHandlerServiceTest {

    @Mock
    private SmtpHandler smtpHandler;

    @Mock
    private SmtpConfig smtpConfig;

    @InjectMocks
    private EmailHandlerServiceImpl emailHandlerService;

    @Test
    void sendEmailTest() {
        Mockito.doNothing().when(smtpHandler).post(any(SmtpEmail.class));

        String address = "some@adreess.com";
        String subject = "testing my subject";
        String content = "the content \n\n bye!";

        when(smtpConfig.getSmtpUsername()).thenReturn("testUserName");
        when(smtpConfig.getSmtpPassword()).thenReturn("testUserPassword");
        
        emailHandlerService.send(new EmailSendRequest(address, subject, content));

        verify(smtpHandler, times(1)).post(any(SmtpEmail.class));
    }
}