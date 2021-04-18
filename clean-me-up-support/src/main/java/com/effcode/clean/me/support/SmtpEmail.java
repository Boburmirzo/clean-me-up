package com.effcode.clean.me.support;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SmtpEmail {

    private String username;
    private String password;
    private String toAddress;
    private String subject;
    private String content;

    @Override
    public String toString() {
        return "SmtpEmail [username=" + username + ", password=" + password + ", adrs=" + toAddress
                + ", subject=" + subject + ", content=" + content + "]";
    }
}
