package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.config.SmtpConfig;
import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailHandlerServiceImpl implements EmailHandlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailHandlerServiceImpl.class);

    private final SmtpHandler smtpHandler;
    private final SmtpConfig smtpConfig;

    public EmailHandlerServiceImpl(SmtpHandler smtpHandler, SmtpConfig smtpConfig) {
        this.smtpHandler = smtpHandler;
        this.smtpConfig = smtpConfig;
    }

    public void send(EmailSendRequest emailSendRequest) {

        LOGGER.debug(emailSendRequest.toString());

        SmtpEmail smtpEmail = new SmtpEmail();
        smtpEmail.setToAddress(emailSendRequest.getToAddress());
        smtpEmail.setSubject(emailSendRequest.getSubject());
        smtpEmail.setContent(emailSendRequest.getContent());
        smtpEmail.setUsername(smtpConfig.getSmtpUsername());
        smtpEmail.setPassword(smtpConfig.getSmtpPassword());
        smtpHandler.post(smtpEmail);

        LOGGER.info(smtpEmail.toString());

    }
}
