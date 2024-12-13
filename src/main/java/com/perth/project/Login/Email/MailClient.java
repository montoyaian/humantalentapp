package com.perth.project.Login.Email;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Configuration
public class MailClient {
    @Bean(name = "emailSession")
    public Session emailSession() {

        final String username = "perthpersoneria@gmail.com";
        final String password = System.getenv("GMAILKEY");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
