package com.perth.project.Officials.FamilyInformation.FamilyInformationTools;

import org.springframework.stereotype.Service;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Officials.FamilyInformation.FamilyInformation;
import com.perth.project.Officials.FamilyInformation.FamilyInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyInformationTools {
    private final FamilyInformationRepository familyInformationRepository;

    public FamilyInformation checkInfo(String id) {
        FamilyInformation familyInformation = familyInformationRepository.findById(id)
                .orElse(null);
        if (familyInformation == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información familiar no está registrada");
        }
        return familyInformation;
    };
}