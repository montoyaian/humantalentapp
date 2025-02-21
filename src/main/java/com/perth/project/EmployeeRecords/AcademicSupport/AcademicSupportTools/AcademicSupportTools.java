package com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools;


import org.springframework.stereotype.Service;

import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupport;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicSupportTools {
    private final AcademicSupportRepository academicSupportRepository;
    private final UserRepository userRepository;

    public AcademicSupport checkInfo(String id) {
        AcademicSupport academicSupport = academicSupportRepository.findById(id)
                .orElse(null);
        if (academicSupport == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de soporte académico no está registrada");
        }
        return academicSupport;
    }

    public String checkIdentification(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));

        if (academicSupportRepository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de soporte académico ya existe");
        }

        return user.getUsername();
    }
}