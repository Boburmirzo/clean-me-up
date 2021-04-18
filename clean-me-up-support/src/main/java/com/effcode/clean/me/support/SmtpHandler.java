package com.effcode.clean.me.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmtpHandler {

    Logger LOGGER = LoggerFactory.getLogger(SmtpHandler.class);

    public void post(SmtpEmail email) {
        LOGGER.debug("Email posted: " + email);
    }
}
