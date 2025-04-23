package com.perth.project.Officials.OfficialsTools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Neighbourhood.Neighbourhood;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodRepository;
import com.perth.project.Officials.Officials;
import com.perth.project.Officials.OfficialsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfficialsFunctions {
    private final OfficialsRepository officialsRepository;
    private final UserRepository userRepository;
    private final NeighbourhoodRepository neighbourhoodRepository;
    public Officials checkInfo(String id){
        Officials personalInformation = officialsRepository.findById(id)
        .orElse(null);
        if (personalInformation == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La informacion de funcionarios no está registrada");
        }
        return personalInformation;
    }  

    public <T> void checkIdentification(String id,JpaRepository<T, String> repository){
        if(!userRepository.findById(id).isPresent()){
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "No existe usuario con esa identificación");
        }
        else if (repository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información ya esta registrada");
        }
    }

    public void checkNeighbourhood(Integer Id){
        Neighbourhood neighbourhood = neighbourhoodRepository.findById(Id)
                                                .orElse(null);
        if (neighbourhood == null){
            throw new BusinessException(
                BusinessErrorCodes.BAD_CREDENTIALS,
                "No existe el barrio ingresado en nuestra base de datos");
        }

    }
}
