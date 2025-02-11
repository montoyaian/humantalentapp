package com.perth.project.Officials.LaboralInformation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.EditLaboralInformationRequest;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.LaboralInformationFunctions;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.LaboralInformationRequest;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.LaboralInformationResponse;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboralInformationService {
    private final LaboralInformationFunctions laboralInformationFunctions;
    private final LaboralInformationRepository laboralInformationRepository;
    private final PersonalInformationFunctions PersonalInformationFunctions;

    public AuthResponse createLaboralInformation(LaboralInformationRequest request) {
        LaboralInformation laboralInformation = LaboralInformation.builder()
                .ID(request.getId())
                .chargeID(request.getChargeID())
                .workAreaID(request.getWorkAreaID())
                .grade(request.getGrade())
                .salary(request.getSalary())
                .typeOfRelationship(request.getTypeOfRelationship())
                .dateOfEntry(request.getDateOfEntry())
                .build();
        PersonalInformationFunctions.checkIdentification(request.getId(),laboralInformationRepository);
        laboralInformationFunctions.validateinfo(request.getChargeID(), request.getWorkAreaID());

        laboralInformationRepository.save(laboralInformation);
        return AuthResponse.builder()
                .response("Información laboral creada correctamente")
                .build();
    }

    public AuthResponse editLaboralInformation(String id, EditLaboralInformationRequest request) {
        LaboralInformation laboralInformation = laboralInformationFunctions.checkInfo(id);
        laboralInformationFunctions.validateinfo(request.getChargeID(), request.getWorkAreaID());
        laboralInformation.setWorkAreaID(request.getWorkAreaID());
        laboralInformation.setGrade(request.getGrade());
        laboralInformation.setSalary(request.getSalary());
        laboralInformation.setTypeOfRelationship(request.getTypeOfRelationship());
        laboralInformation.setDateOfEntry(request.getDateOfEntry());

        laboralInformationRepository.save(laboralInformation);
        return AuthResponse.builder()
                .response("Información laboral editada correctamente")
                .build();
    }

    public AuthResponse deleteLaboralInformation(String id) {
        LaboralInformation laboralInformation = laboralInformationFunctions.checkInfo(id);
        laboralInformationRepository.delete(laboralInformation);
        return AuthResponse.builder()
                .response("Información laboral eliminada correctamente")
                .build();
    }

    public Object readLaboralInformation(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<LaboralInformation> laboralInformations = laboralInformationRepository.findAll();
            List<LaboralInformationResponse> laboralInformationResponses = laboralInformations.stream()
                    .map(laboralInformation -> new LaboralInformationResponse(
                            laboralInformation.getID(),
                            laboralInformation.getChargeID(),
                            laboralInformation.getWorkAreaID(),
                            laboralInformation.getGrade(),
                            laboralInformation.getSalary(),
                            laboralInformation.getTypeOfRelationship(),
                            laboralInformation.getDateOfEntry()))
                    .collect(Collectors.toList());
            return laboralInformationResponses;
        } else {
            LaboralInformation laboralInformation = laboralInformationFunctions.checkInfo(id);
            return new LaboralInformationResponse(
                    laboralInformation.getID(),
                    laboralInformation.getChargeID(),
                    laboralInformation.getWorkAreaID(),
                    laboralInformation.getGrade(),
                    laboralInformation.getSalary(),
                    laboralInformation.getTypeOfRelationship(),
                    laboralInformation.getDateOfEntry());
        }
    }
}
