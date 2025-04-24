package com.perth.project.Cv.Disabilities.DisabilitiesTools;

import org.springframework.stereotype.Service;

import com.perth.project.Cv.Disabilities.Disabilities;
import com.perth.project.Cv.Disabilities.DisabilitiesRepository;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisabilitiesTools {
    private final DisabilitiesRepository disabilitiesRepository;
    private final UserRepository userRepository;

    public Disabilities checkInfo(String id) {
        Disabilities disabilities = disabilitiesRepository.findById(id)
                .orElse(null);
        if (disabilities == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de discapacidad no está registrada");
        }
        return disabilities;
    }

    public String checkIdentification(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));

        if (disabilitiesRepository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de discapacidad ya existe");
        }

        return user.getUsername();
    }
}