package com.perth.project.Officials.PersonalInformation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.EditPersonalInformationRequest;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationFunctions;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationRequest;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInformationService {
    
    private final PersonalInformationRepository personalInformationRepository;
    private final PersonalInformationFunctions personalInformationFunctions;

    public AuthResponse createPersonalInformation(PersonalInformationRequest request) {
        Personal_Information personalInformation = Personal_Information.builder()
                .ID(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .genre(request.getGenre())
                .civilStatus(request.getCivilStatus())
                .phone(request.getPhone())
                .email(request.getEmail())
                .neighbourhoodID(request.getNeighbourhoodID())
                .address(request.getAddress())
                .birthday(request.getBirthday())
                .build();
        personalInformationFunctions.checkNeighbourhood(personalInformation.getNeighbourhoodID());
        personalInformationFunctions.checkIdentification(request.getId(),personalInformationRepository);
        personalInformationRepository.save(personalInformation);
        return AuthResponse.builder()
                .response("Información personal creada correctamente")
                .build();
        
    }

    public AuthResponse editPersonalInformation(String id, EditPersonalInformationRequest request) {
        Personal_Information personalInformation = personalInformationFunctions.checkInfo(id);
        personalInformation.setFirstName(request.getFirstName());
        personalInformation.setLastName(request.getLastName());
        personalInformation.setGenre(request.getGenre());
        personalInformation.setCivilStatus(request.getCivilStatus());
        personalInformation.setPhone(request.getPhone());
        personalInformation.setEmail(request.getEmail());
        personalInformation.setNeighbourhoodID(request.getNeighbourhoodID());
        personalInformation.setAddress(request.getAddress());
        personalInformation.setBirthday(request.getBirthday());
        personalInformationFunctions.checkNeighbourhood(personalInformation.getNeighbourhoodID());
        personalInformationRepository.save(personalInformation);
        return AuthResponse.builder()
                .response("Información personal editada correctamente")
                .build();
    }

    public AuthResponse deletePersonalInformation(String id) {
        Personal_Information personalInformation = personalInformationFunctions.checkInfo(id);
        personalInformationRepository.delete(personalInformation);
        return AuthResponse.builder()
                .response("Información personal eliminada correctamente")
                .build();
    }

    public Object readPersonalInformation(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<Personal_Information> personalInformations = personalInformationRepository.findAll();
            List<PersonalInformationResponse> personalInformationResponses = personalInformations.stream()
                    .map(personalInformation -> new PersonalInformationResponse(
                            personalInformation.getID(),
                            personalInformation.getFirstName(),
                            personalInformation.getLastName(),
                            personalInformation.getGenre(),
                            personalInformation.getCivilStatus(),
                            personalInformation.getPhone(),
                            personalInformation.getEmail(),
                            personalInformation.getNeighbourhoodID(),
                            personalInformation.getAddress(),
                            personalInformation.getBirthday()))
                    .collect(Collectors.toList());
            return personalInformationResponses;
        } else {
            Personal_Information personalInformation = personalInformationFunctions.checkInfo(id);
            return new PersonalInformationResponse(
                    personalInformation.getID(),
                    personalInformation.getFirstName(),
                    personalInformation.getLastName(),
                    personalInformation.getGenre(),
                    personalInformation.getCivilStatus(),
                    personalInformation.getPhone(),
                    personalInformation.getEmail(),
                    personalInformation.getNeighbourhoodID(),
                    personalInformation.getAddress(),
                    personalInformation.getBirthday());
        }
    }
}