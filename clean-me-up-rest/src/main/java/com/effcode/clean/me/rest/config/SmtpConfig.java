package com.effcode.clean.me.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmtpConfig {

    @Value("${smtp.email.username}")
    private String smtpUsername;

    @Value("${smtp.email.password}")
    private String smtpPassword;

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

}
