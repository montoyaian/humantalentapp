package com.perth.project.Login.User.UserFuntions.Notification;

import javax.mail.*;
import javax.mail.internet.*;
import com.perth.project.Login.Email.EmailFuntions;

public class PasswordResetNotification {

    public static void sendNotification(String userEmail, String userName, Session emailSession, String token) throws MessagingException {
        String filePath = EmailFuntions.pathTemplate("ResetPassword.html");
        String content = EmailFuntions.readTemplate(filePath);
        String templateReplace = TemplateUtils.replaceValues(content, token);

        Message message = new MimeMessage(emailSession);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
        message.setSubject("Restablecimiento de Contraseña");
        message.setContent(templateReplace, "text/html; charset=utf-8");

        Transport.send(message);
    }
}