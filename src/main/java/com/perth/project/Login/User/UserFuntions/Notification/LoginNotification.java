package com.perth.project.Login.User.UserFuntions.Notification;

import javax.mail.*;
import javax.mail.internet.*;
import com.perth.project.Login.Email.EmailFuntions;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

public class LoginNotification  {

    public static void sendNotification(String userEmail, String userName, String Password,Session emailSession) throws MessagingException {
        String filePath = EmailFuntions.pathTemplate("EmailWelcome.html");
        String content = EmailFuntions.readTemplate(filePath);
        String templateReplace = TemplateUtils.replaceValues(userName, "", content);
        try {
            Message message = new MimeMessage(emailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Inicio de Sesión");
            message.setContent(templateReplace, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "Error al enviar el correo");
        }     
    }
}