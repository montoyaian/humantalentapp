package com.perth.project.Officials.PersonalInformation.PersonalInformationTools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Officials.PersonalInformation.Personal_Information;
import com.perth.project.Officials.PersonalInformation.PersonalInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInformationFunctions {
    private final PersonalInformationRepository personalInformationRepository;
    private final UserRepository userRepository;
    public Personal_Information checkInfo(String id){
        Personal_Information personalInformation = personalInformationRepository.findById(id)
        .orElse(null);
        if (personalInformation == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información personal no está registrada");
        }
        return personalInformation;
    }  

    public <T> void checkIdentification(String id,JpaRepository<T, String> repository){
        if(!userRepository.findByIdentification(id).isPresent()){
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "No existe usuario con esa identificación");
        }
        else if (repository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información personal ya existe");
        }
    }
}
