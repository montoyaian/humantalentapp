package com.perth.project.Parameterization.Charge.ChargeTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Charge.Charge;
import com.perth.project.Parameterization.Charge.ChargeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChargeFunctions {
    private final ChargeRepository ChargeRepository;

    public Charge checkCharge(Integer id) {
        Charge Charge = ChargeRepository.findById(id)
                .orElse(null);

        if (Charge == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El cargo no está registrado");
        } else {
            return Charge;
        }
    }
}
