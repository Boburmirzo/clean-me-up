package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.model.requests.EmailSendRequest;

public interface EmailHandlerService {

    void send(EmailSendRequest emailSendRequest);
}
