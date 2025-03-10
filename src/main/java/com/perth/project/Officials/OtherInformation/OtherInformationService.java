package com.perth.project.Officials.OtherInformation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.OtherInformationRequest;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.OtherInformationResponse;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.OtherInformationTools;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.OfficialsFunctions;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.EditOtherInformation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtherInformationService {
    
    private final OtherInformationRepository otherInformationRepository;
    private final OtherInformationTools otherInformationTools;
    private final OfficialsFunctions PersonalInformationFunctions;
    public AuthResponse createOtherInformation(OtherInformationRequest request) {

        OtherInformation otherInformation = OtherInformation.builder()
                .userId(request.getId())
                .disability(request.getDisability())
                .languages(request.getLanguages())
                .EPS(request.getEps())
                .AFP(request.getAfp())
                .BloodType(request.getBloodType())
                .Syndicate(request.getSyndicate())
                .Audited(request.getAudited())
                .disease(request.getDisease())
                .Hobby(request.getHobby())
                .prePensioned(request.getPrePensioned())
                .HeadOfHousehold(request.getHeadOfHousehold())
                .build();
        PersonalInformationFunctions.checkIdentification(request.getId(),otherInformationRepository);
        otherInformationRepository.save(otherInformation);
        return AuthResponse.builder()
                .response("Información adicional creada correctamente")
                .build();
    }

    public AuthResponse editOtherInformation(String id, EditOtherInformation request) {
        OtherInformation otherInformation = otherInformationTools.checkInfo(id);
        otherInformation.setDisability(request.getDisability());
        otherInformation.setLanguages(request.getLanguages());
        otherInformation.setEPS(request.getEPS());
        otherInformation.setAFP(request.getAFP());
        otherInformation.setBloodType(request.getBloodType());
        otherInformation.setSyndicate(request.getSyndicate());
        otherInformation.setAudited(request.getAudited());
        otherInformation.setDisease(request.getDisease());
        otherInformation.setHobby(request.getHobby());
        otherInformation.setPrePensioned(request.getPrePensioned());
        otherInformation.setHeadOfHousehold(request.getHeadOfHousehold());

        otherInformationRepository.save(otherInformation);
        return AuthResponse.builder()
                .response("Información adicional editada correctamente")
                .build();
    }

    public AuthResponse deleteOtherInformation(String id) {
        OtherInformation otherInformation = otherInformationTools.checkInfo(id);
        otherInformationRepository.delete(otherInformation);
        return AuthResponse.builder()
                .response("Información adicional eliminada correctamente")
                .build();
    }

    public Object readOtherInformation(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<OtherInformation> otherInformations = otherInformationRepository.findAll();
            List<OtherInformationResponse> otherInformationResponses = otherInformations.stream()
                    .map(otherInformation -> new OtherInformationResponse(
                            otherInformation.getUserId(),
                            otherInformation.getDisability(),
                            otherInformation.getLanguages(),
                            otherInformation.getEPS(),
                            otherInformation.getAFP(),
                            otherInformation.getBloodType(),
                            otherInformation.getSyndicate(),
                            otherInformation.getAudited(),
                            otherInformation.getDisease(),
                            otherInformation.getHobby(),
                            otherInformation.getPrePensioned(),
                            otherInformation.getHeadOfHousehold()))
                    .collect(Collectors.toList());
            return otherInformationResponses;
        } else {
            OtherInformation otherInformation = otherInformationTools.checkInfo(id);
            return new OtherInformationResponse(
                    otherInformation.getUserId(),
                    otherInformation.getDisability(),
                    otherInformation.getLanguages(),
                    otherInformation.getEPS(),
                    otherInformation.getAFP(),
                    otherInformation.getBloodType(),
                    otherInformation.getSyndicate(),
                    otherInformation.getAudited(),
                    otherInformation.getDisease(),
                    otherInformation.getHobby(),
                    otherInformation.getPrePensioned(),
                    otherInformation.getHeadOfHousehold());
        }
    }
}