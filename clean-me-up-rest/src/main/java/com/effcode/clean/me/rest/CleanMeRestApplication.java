package com.effcode.clean.me.rest;

import com.effcode.clean.me.support.SmtpHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CleanMeRestApplication {
    
    @Bean
    public SmtpHandler smtpHandler() {
        return new SmtpHandler();
    }

	public static void main(String[] args) {
		SpringApplication.run(CleanMeRestApplication.class, args);
	}
}
