package com.perth.project.Cv.LaboralExperience.LaboralExperienceTools;

import org.springframework.stereotype.Service;

import com.perth.project.Cv.LaboralExperience.LaboralExperience;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceRepository;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboralExperienceTools {
    private final LaboralExperienceRepository laboralExperienceRepository;
    private final UserRepository userRepository;

    public LaboralExperience checkInfo(String id) {
        LaboralExperience laboralExperience = laboralExperienceRepository.findById(id)
                .orElse(null);
        if (laboralExperience == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de experiencia laboral no está registrada");
        }
        return laboralExperience;
    }

    public String checkIdentification(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));

        if (laboralExperienceRepository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de experiencia laboral ya existe");
        }

        return user.getUsername();
    }
}