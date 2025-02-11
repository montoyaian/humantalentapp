package com.perth.project.Officials.OtherInformation.OtherInformationTools;

import org.springframework.stereotype.Service;


import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Officials.OtherInformation.OtherInformation;
import com.perth.project.Officials.OtherInformation.OtherInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtherInformationTools {
    private final OtherInformationRepository otherInformationRepository;
 

    public OtherInformation checkInfo(String id) {
        OtherInformation otherInformation = otherInformationRepository.findById(id)
                .orElse(null);
        if (otherInformation == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información adicional no está registrada");
        }
        return otherInformation;
    }

}