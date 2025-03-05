package com.perth.project.Parameterization.Area.AreaTools;
import org.springframework.stereotype.Service;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Area.Area;
import com.perth.project.Parameterization.Area.AreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class areaFunctions {
    private final AreaRepository areaRepository;
    
    public Area checkArea(Integer code){
        Area area = areaRepository.findByCode(code)
                .orElse(null);

        if (area == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El area no esta registrada");
        }
        return area;
        
    }

    public void checkAreaExit(Integer code, String name) {
        if (areaRepository.findByCode(code).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El área ya está registrada por código");
        }
    
        if (areaRepository.findByName(name).isPresent()) {
            throw new BusinessException(                                             
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El área ya está registrada por nombre");
        }
    }
}    
