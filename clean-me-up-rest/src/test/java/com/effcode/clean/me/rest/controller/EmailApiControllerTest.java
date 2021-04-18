package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.model.requests.EmailSendRequest;
import com.effcode.clean.me.rest.service.EmailHandlerService;
import com.effcode.clean.me.rest.validator.EmailValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmailApiController.class)
class EmailApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmailValidator emailValidator;
    @MockBean
    private EmailHandlerService emailHandler;

    @Value("${smtp.email.username}")
    private String smtpUsername;

    @Value("${smtp.email.password}")
    private String smtpPassword;


    @Test
    void dontAllowInvalidRole() throws Exception {
        mockMvc.perform(post("/api/send")
                .with(user(smtpUsername).password(smtpPassword))
                .contentType("application/json")
                .content(getNewEmail())
        ).andExpect(status().isForbidden());
    }

    @Test
    void dontAllowUnauthorizedSending() throws Exception {
        mockMvc.perform(post("/api/send")
                .contentType("application/json")
                .content(getNewEmail())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void sendEmptyBodyEmail() throws Exception {
        mockMvc.perform(post("/api/send")
                .with(user(smtpUsername).password(smtpPassword))
                .contentType("application/json")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void sendEmail() throws Exception {
        mockMvc.perform(post("/api/send")
                .with(user(smtpUsername).password(smtpPassword).roles("MAIL_SENDER"))
                .contentType("application/json")
                .content(getNewEmail())
        ).andExpect(status().isNoContent());
    }


    private String getNewEmail() {
        try {
            return objectMapper.writeValueAsString(new EmailSendRequest("my@test.se", "lala", "hej hej\n\npå dig!"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to create test email string.", e);
        }
    }

}