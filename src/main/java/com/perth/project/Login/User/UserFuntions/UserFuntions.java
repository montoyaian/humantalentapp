package com.perth.project.Login.User.UserFuntions;

import java.util.Random;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Area.AreaRepository;
import com.perth.project.Parameterization.Charge.ChargeRepository;

@Service
@RequiredArgsConstructor
public class UserFuntions {

    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final ChargeRepository chargeRepository;

    public void Validation(User user) {
        UserDetails emailFound = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user.getID().length() != 10) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "La identificación debe tener 10 caracteres");

        } else if (emailFound != null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El correo ingresado ya existe en nuestra base de datos");
        }else if (areaRepository.findById(user.getArea()).isEmpty()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El area ingresada no existe en nuestra base de datos");
        }else if(chargeRepository.findById(user.getProfile()).isEmpty()){
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El perfil ingresado no existe en nuestra base de datos");
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
