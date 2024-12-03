package com.perth.project.Login.User;

import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.security.core.userdetails.UserDetails;
import com.perth.project.Login.Auth.AuthResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.perth.project.Login.Email.EmailFuntions;

@Service
@RequiredArgsConstructor
public class UserFuntions {

    private final UserRepository userRepository;

    public AuthResponse Validation(User user) {

        String usernamePattern = "^[a-zA-Z0-9]+$";

        if (!user.getUsername().matches(usernamePattern)) {
            return AuthResponse.builder()
                    .response("No se permiten caracteres especiales en el nombre")
                    .build();
        }
        if (user.getIdentification().length() != 10) {
            return AuthResponse.builder()
                    .response("Identificacion no valida")
                    .build();

        }
        UserDetails userfound = userRepository.findByIdentification(user.getIdentification()).orElse(null);
        if (userfound != null) {
            return AuthResponse.builder()
                    .response("El usuario ya existe")
                    .build();
        }
        userRepository.save(user);

        return null;
    }

    public static void Notification(String userEmail, String filePath, String userName, Session emailSession) {

        String password = EmailFuntions.generatePassword();
        String content = EmailFuntions.readTemplate(filePath);
        String templateReplace = EmailFuntions.replaceValues(userName, password, content);
        try {
            Message message = new MimeMessage(emailSession);

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Inicio de seccion");
            message.setContent(templateReplace, "text/html; charset=utf-8");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
