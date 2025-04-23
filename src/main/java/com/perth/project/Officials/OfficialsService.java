package com.perth.project.Officials;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.OfficialsTools.EditOfficialsRequest;
import com.perth.project.Officials.OfficialsTools.OfficialsFunctions;
import com.perth.project.Officials.OfficialsTools.OfficialsRequest;
import com.perth.project.Officials.OfficialsTools.OfficialsResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficialsService {
    
    private final OfficialsRepository OfficialsRepository;
    private final OfficialsFunctions OfficialsFunctions;

    public AuthResponse createOfficials(OfficialsRequest request) {
        Officials officials = Officials.builder()
                .UserId(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .genre(request.getGenre())
                .civilStatus(request.getCivilStatus())
                .phone(request.getPhone())
                .email(request.getEmail())
                .neighbourhoodID(request.getNeighbourhoodID())
                .address(request.getAddress())
                .birthday(request.getBirthday())
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
                .chargeID(request.getChargeID())
                .workAreaID(request.getWorkAreaID())
                .grade(request.getGrade())
                .salary(request.getSalary())
                .typeOfRelationship(request.getTypeOfRelationship())
                .dateOfEntry(request.getDateOfEntry())
                .married(request.isMarried())
                .sons(request.getSons())
                .profession(request.getProfession())
                .lastStudyType(request.getLastStudyType())
                .build();
        OfficialsFunctions.checkNeighbourhood(officials.getNeighbourhoodID());
        OfficialsFunctions.checkIdentification(request.getId(), OfficialsRepository);
        OfficialsRepository.save(officials);
        return AuthResponse.builder()
                .response("Información personal creada correctamente")
                .build();
    }

    public AuthResponse editOfficials(String id, EditOfficialsRequest request) {
        Officials Officials = OfficialsFunctions.checkInfo(id);
        Officials.setFirstName(request.getFirstName());
        Officials.setLastName(request.getLastName());
        Officials.setGenre(request.getGenre());
        Officials.setCivilStatus(request.getCivilStatus());
        Officials.setPhone(request.getPhone());
        Officials.setEmail(request.getEmail());
        Officials.setNeighbourhoodID(request.getNeighbourhoodID());
        Officials.setAddress(request.getAddress());
        Officials.setBirthday(request.getBirthday());
        Officials.setDisability(request.getDisability());
        Officials.setLanguages(request.getLanguages());
        Officials.setEPS(request.getEPS());
        Officials.setAFP(request.getAFP());
        Officials.setBloodType(request.getBloodType());
        Officials.setSyndicate(request.getSyndicate());
        Officials.setAudited(request.getAudited());
        Officials.setDisease(request.getDisease());
        Officials.setHobby(request.getHobby());
        Officials.setPrePensioned(request.getPrePensioned());
        Officials.setHeadOfHousehold(request.getHeadOfHousehold());
        Officials.setChargeID(request.getChargeID());
        Officials.setWorkAreaID(request.getWorkAreaID());
        Officials.setGrade(request.getGrade());
        Officials.setSalary(request.getSalary());
        Officials.setTypeOfRelationship(request.getTypeOfRelationship());
        Officials.setDateOfEntry(request.getDateOfEntry());
        Officials.setMarried(request.isMarried());
        Officials.setSons(request.getSons());
        Officials.setProfession(request.getProfession());
        Officials.setLastStudyType(request.getLastStudyType());
        OfficialsFunctions.checkNeighbourhood(Officials.getNeighbourhoodID());
        OfficialsRepository.save(Officials);
        return AuthResponse.builder()
                .response("Información personal editada correctamente")
                .build();
    }

    public AuthResponse deleteOfficials(String id) {
        Officials Officials = OfficialsFunctions.checkInfo(id);
        OfficialsRepository.delete(Officials);
        return AuthResponse.builder()
                .response("Información personal eliminada correctamente")
                .build();
    }

    public Object readOfficials(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<Officials> Officialss = OfficialsRepository.findAll();
            List<OfficialsResponse> OfficialsResponses = Officialss.stream()
                    .map(Officials -> new OfficialsResponse(
                            Officials.getUserId(),
                            Officials.getFirstName(),
                            Officials.getLastName(),
                            Officials.getGenre(),
                            Officials.getCivilStatus(),
                            Officials.getPhone(),
                            Officials.getEmail(),
                            Officials.getNeighbourhoodID(),
                            Officials.getAddress(),
                            Officials.getBirthday(),
                            Officials.getDisability(),
                            Officials.getLanguages(),
                            Officials.getEPS(),
                            Officials.getAFP(),
                            Officials.getBloodType(),
                            Officials.getSyndicate(),
                            Officials.getAudited(),
                            Officials.getDisease(),
                            Officials.getHobby(),
                            Officials.getPrePensioned(),
                            Officials.getHeadOfHousehold(),
                            Officials.getChargeID(),
                            Officials.getWorkAreaID(),
                            Officials.getGrade(),
                            Officials.getSalary(),
                            Officials.getTypeOfRelationship(),
                            Officials.getDateOfEntry(),
                            Officials.isMarried(),
                            Officials.getSons(),
                            Officials.getProfession(),
                            Officials.getLastStudyType()
                    ))
                    .collect(Collectors.toList());
            return OfficialsResponses;
        } else {
            Officials Officials = OfficialsFunctions.checkInfo(id);
            return new OfficialsResponse(
                    Officials.getUserId(),
                    Officials.getFirstName(),
                    Officials.getLastName(),
                    Officials.getGenre(),
                    Officials.getCivilStatus(),
                    Officials.getPhone(),
                    Officials.getEmail(),
                    Officials.getNeighbourhoodID(),
                    Officials.getAddress(),
                    Officials.getBirthday(),
                    Officials.getDisability(),
                    Officials.getLanguages(),
                    Officials.getEPS(),
                    Officials.getAFP(),
                    Officials.getBloodType(),
                    Officials.getSyndicate(),
                    Officials.getAudited(),
                    Officials.getDisease(),
                    Officials.getHobby(),
                    Officials.getPrePensioned(),
                    Officials.getHeadOfHousehold(),
                    Officials.getChargeID(),
                    Officials.getWorkAreaID(),
                    Officials.getGrade(),
                    Officials.getSalary(),
                    Officials.getTypeOfRelationship(),
                    Officials.getDateOfEntry(),
                    Officials.isMarried(),
                    Officials.getSons(),
                    Officials.getProfession(),
                    Officials.getLastStudyType()
            );
        }
    }
}