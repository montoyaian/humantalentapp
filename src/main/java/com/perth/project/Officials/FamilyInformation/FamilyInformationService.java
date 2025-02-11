package com.perth.project.Officials.FamilyInformation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.FamilyInformationRequest;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.FamilyInformationResponse;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.FamilyInformationTools;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationFunctions;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.EditFamilyInformation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamilyInformationService {
    
    private final FamilyInformationRepository familyInformationRepository;
    private final FamilyInformationTools familyInformationTools;
    private final PersonalInformationFunctions PersonalInformationFunctions;

    public AuthResponse createFamilyInformation(FamilyInformationRequest request) {
        FamilyInformation familyInformation = FamilyInformation.builder()
                .ID(request.getId())
                .married(request.isMarried())
                .sons(request.getSons())
                .build();
        PersonalInformationFunctions.checkIdentification(request.getId(),familyInformationRepository);
        familyInformationRepository.save(familyInformation);
        return AuthResponse.builder()
                .response("Información familiar creada correctamente")
                .build();
    }

    public AuthResponse editFamilyInformation(String id, EditFamilyInformation request) {
        FamilyInformation familyInformation = familyInformationTools.checkInfo(id);
        familyInformation.setMarried(request.isMarried());
        familyInformation.setSons(request.getSons());

        familyInformationRepository.save(familyInformation);
        return AuthResponse.builder()
                .response("Información familiar editada correctamente")
                .build();
    }

    public AuthResponse deleteFamilyInformation(String id) {
        FamilyInformation familyInformation = familyInformationTools.checkInfo(id);
        familyInformationRepository.delete(familyInformation);
        return AuthResponse.builder()
                .response("Información familiar eliminada correctamente")
                .build();
    }

    public Object readFamilyInformation(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<FamilyInformation> familyInformations = familyInformationRepository.findAll();
            List<FamilyInformationResponse> familyInformationResponses = familyInformations.stream()
                    .map(familyInformation -> new FamilyInformationResponse(
                            familyInformation.getID(),
                            familyInformation.isMarried(),
                            familyInformation.getSons()))
                    .collect(Collectors.toList());
            return familyInformationResponses;
        } else {
            FamilyInformation familyInformation = familyInformationTools.checkInfo(id);
            return new FamilyInformationResponse(
                    familyInformation.getID(),
                    familyInformation.isMarried(),
                    familyInformation.getSons());
        }
    }
}