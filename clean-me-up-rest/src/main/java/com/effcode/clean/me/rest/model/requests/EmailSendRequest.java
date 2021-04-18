package com.effcode.clean.me.rest.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Email message class for email send request
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailSendRequest {
    private String toAddress;
    private String subject;
    private String content;
}
