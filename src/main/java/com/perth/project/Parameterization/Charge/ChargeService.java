package com.perth.project.Parameterization.Charge;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.Charge.ChargeTools.ChargeRequest;
import com.perth.project.Parameterization.Charge.ChargeTools.ChargeResponse;
import com.perth.project.Parameterization.Charge.ChargeTools.ChargeFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChargeService {

    private final ChargeRepository ChargeRepository;
    private final ChargeFunctions ChargeFunctions;

    public AuthResponse createCharge(ChargeRequest request) {
        Charge charge = Charge.builder()
                .nombre(request.getName())
                .build();
 
        ChargeRepository.save(charge);
        return AuthResponse.builder()
                .response("cargo creado correctamente")
                .build();
       
    }

    public AuthResponse editCharge(Integer id, ChargeRequest request) {
        Charge Charge = ChargeFunctions.checkCharge(id);
        Charge.setNombre(request.getName());

        ChargeRepository.save(Charge);
        return AuthResponse.builder()
                .response("Charge editado correctamente")
                .build();
    }

    public AuthResponse deleteCharge(Integer id) {
        Charge Charge = ChargeFunctions.checkCharge(id);
        ChargeRepository.delete(Charge);
        return AuthResponse.builder()
                .response("Charge eliminado correctamente")
                .build();
    }

    public Object readCharge(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<Charge> Charges = ChargeRepository.findAll();
            List<ChargeResponse> ChargeResponses = Charges.stream()
                    .map(Charge -> new ChargeResponse(Charge.getId(), Charge.getNombre()))
                    .collect(Collectors.toList());
            return ChargeResponses;
        } else {
            Charge Charge = ChargeFunctions.checkCharge(Integer.valueOf(id));
            return new ChargeResponse(Charge.getId(), Charge.getNombre());
        }
    }
}