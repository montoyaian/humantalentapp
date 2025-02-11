package com.perth.project.Officials.AcademicInformation.AcademicInformationTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Officials.AcademicInformation.AcademicInformation;
import com.perth.project.Officials.AcademicInformation.AcademicInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicInformationTools {
    private final AcademicInformationRepository academicInformationRepository;
    

    public AcademicInformation checkInfo(String id) {
            AcademicInformation academicInformation = academicInformationRepository.findById(id)
                    .orElse(null);
            if (academicInformation == null) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "La información academica no está registrada");
            }
            return academicInformation;
    }

}
