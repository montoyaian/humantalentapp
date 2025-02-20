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
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Area.AreaRepository;

@Service
@RequiredArgsConstructor
public class UserFuntions {

    private final UserRepository userRepository;
    private final AreaRepository areaRepository;

    public void Validation(User user) {
        UserDetails userfound = userRepository.findByIdentification(user.getIdentification()).orElse(null);
        UserDetails emailFound = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user.getIdentification().length() != 10) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "La identificación debe tener 10 caracteres");

        } else if (userfound != null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "La identificación ingresada ya existe en nuestra base de datos");
        } else if (emailFound != null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El correo ingresado ya existe en nuestra base de datos");
        }else if (areaRepository.findByName(user.getArea()).isEmpty()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El area ingresada no existe en nuestra base de datos");
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
