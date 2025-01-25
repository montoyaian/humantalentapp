package com.perth.project.Login.User.UserFuntions;

import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.security.core.userdetails.UserDetails;
import com.perth.project.Login.Auth.AuthResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.perth.project.Login.Email.EmailFuntions;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;

@Service
@RequiredArgsConstructor
public class UserFuntions {

    private final UserRepository userRepository;

    public AuthResponse Validation(User user) {
        UserDetails userfound = userRepository.findByIdentification(user.getIdentification()).orElse(null);
        UserDetails emailFound = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user.getIdentification().length() != 10) {
            return AuthResponse.builder()
                    .response("Identificacion no valida")
                    .build();

        } else if (userfound != null) {
            return AuthResponse.builder()
                    .response("La identificacion del usuario ya esta registrada")
                    .build();
        } else if (emailFound != null) {
            return AuthResponse.builder()
                    .response("El Email del usuario ya esta registrado")
                    .build();
        } else {
            return null;
        }
    }

    public static String generatePassword() {
        int length = 8;

        String numbers = "0123456789";

        Random random = new Random();

        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numbers.length());
            password.append(numbers.charAt(index));
        }

        return password.toString();
    }

    public static String Notification(String userEmail, String filePath, String userName, Session emailSession,
            String password) throws MessagingException {

        String content = EmailFuntions.readTemplate(filePath);
        System.out.println(password);
        String templateReplace = EmailFuntions.replaceValues(userName, password, content);
        try {
            Message message = new MimeMessage(emailSession);

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Inicio de seccion");
            message.setContent(templateReplace, "text/html; charset=utf-8");

            Transport.send(message);
            return ("Correo enviado");
        } catch (MessagingException e) {
            throw new MessagingException(e.getMessage(), e);
        }
    }

    public String CreateUserName(String firstName, String lastName) {
        Integer i = 0;

        while (true) {
            String firstLetter = firstName.substring(0, i + 1); // Asegúrate de que i + 1 no exceda la longitud
            String firstWordLastName = lastName.split(" ")[0];
            String username = firstLetter + firstWordLastName.toLowerCase();

            UserDetails userfound = userRepository.findByUsername(username).orElse(null);

            if (userfound == null) {
                return username;
            }

            i++;
        }
    }
}
