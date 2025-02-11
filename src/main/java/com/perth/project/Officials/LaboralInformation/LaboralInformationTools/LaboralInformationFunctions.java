package com.perth.project.Officials.LaboralInformation.LaboralInformationTools;

import org.springframework.stereotype.Service;


import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Officials.LaboralInformation.LaboralInformation;
import com.perth.project.Officials.LaboralInformation.LaboralInformationRepository;
import com.perth.project.Parameterization.Area.AreaRepository;
import com.perth.project.Parameterization.Charge.ChargeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboralInformationFunctions {
    private final LaboralInformationRepository laboralInformationRepository;

    private final AreaRepository areaRepository;
    private final ChargeRepository chargeRepository;

    public LaboralInformation checkInfo(String id) {
        LaboralInformation laboralInformation = laboralInformationRepository.findById(id)
                .orElse(null);
        if (laboralInformation == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información laboral no está registrada");
        }
        return laboralInformation;
    }

    public void validateinfo(Integer ChargeID, Integer workAreaID) {
        if(!chargeRepository.findById(ChargeID).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El cargo no existe");
        } else if(!areaRepository.findByCode(workAreaID).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El área de trabajo no existe");
        }
    }
}