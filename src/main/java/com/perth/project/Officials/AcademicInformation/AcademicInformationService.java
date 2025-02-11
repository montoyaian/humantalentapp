package com.perth.project.Officials.AcademicInformation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.AcademicInformationRequest;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.AcademicInformationResponse;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.AcademicInformationTools;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.EditAcademicInformation;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicInformationService {
    
    private final AcademicInformationRepository academicInformationRepository;
    private final AcademicInformationTools academicInformationTools;
    private final PersonalInformationFunctions PersonalInformationFunctions;
    public AuthResponse createAcademicInformation(AcademicInformationRequest request) {
        AcademicInformation academicInformation = AcademicInformation.builder()
                .ID(request.getId()) 
                .profession(request.getProfession())
                .lastStudyType(request.getLastStudyType())
                .build();
        PersonalInformationFunctions.checkIdentification(request.getId(),academicInformationRepository);
        academicInformationRepository.save(academicInformation);
        return AuthResponse.builder()
                .response("Información académica creada correctamente")
                .build();
    }

    public AuthResponse editAcademicInformation(String id, EditAcademicInformation request) {
        AcademicInformation academicInformation = academicInformationTools.checkInfo(id);
        academicInformation.setProfession(request.getProfession());
        academicInformation.setLastStudyType(request.getLastStudyType());

        academicInformationRepository.save(academicInformation);
        return AuthResponse.builder()
                .response("Información académica editada correctamente")
                .build();
    }

    public AuthResponse deleteAcademicInformation(String id) {
        AcademicInformation academicInformation = academicInformationTools.checkInfo(id);
        academicInformationRepository.delete(academicInformation);
        return AuthResponse.builder()
                .response("Información académica eliminada correctamente")
                .build();
    }

    public Object readAcademicInformation(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<AcademicInformation> academicInformations = academicInformationRepository.findAll();
            List<AcademicInformationResponse> academicInformationResponses = academicInformations.stream()
                    .map(academicInformation -> new AcademicInformationResponse(
                            academicInformation.getID(),
                            academicInformation.getProfession(),
                            academicInformation.getLastStudyType()))
                    .collect(Collectors.toList());
            return academicInformationResponses;
        } else {
            AcademicInformation academicInformation = academicInformationTools.checkInfo(id);
            return new AcademicInformationResponse(
                    academicInformation.getID(),
                    academicInformation.getProfession(),
                    academicInformation.getLastStudyType());
        }
    }
}