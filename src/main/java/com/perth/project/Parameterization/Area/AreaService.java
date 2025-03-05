package com.perth.project.Parameterization.Area;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;

import com.perth.project.Parameterization.Area.AreaTools.AreaRequest;
import com.perth.project.Parameterization.Area.AreaTools.AreaResponse;
import com.perth.project.Parameterization.Area.AreaTools.EditAreaRequest;
import com.perth.project.Parameterization.Area.AreaTools.areaFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AreaService {
    
    private final AreaRepository areaRepository; 
    private final areaFunctions areaFunctions;
    public AuthResponse createArea(AreaRequest request){
        Area area = Area.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
        areaFunctions.checkAreaExit(area.getCode(),area.getName());
        areaRepository.save(area);
        return AuthResponse.builder()
            .response("Area creada correctamente")
            .build();
    }

    public AuthResponse editArea(Integer code, EditAreaRequest request) {
        Area area = areaFunctions.checkArea(code);
        area.setName(request.getName());

        areaRepository.save(area);
        return AuthResponse.builder()
                .response("Area editada correctamente")
                .build();
    }

    public AuthResponse deleteArea(Integer code) {
        Area area = areaFunctions.checkArea(code);
        areaRepository.delete(area);
        return AuthResponse.builder()
                .response("Area eliminada correctamente")
                .build();
    }

    public Object readArea(String code) {
        if ("all".equalsIgnoreCase(code)) {
            List<Area> areas = areaRepository.findAll();
            List<AreaResponse> areaResponses = areas.stream()
                    .map(area -> new AreaResponse(area.getCode(), area.getName()))
                    .collect(Collectors.toList());
            return areaResponses;
        } else {
            Area area = areaFunctions.checkArea(Integer.valueOf(code));
            if (area == null) {
                return AuthResponse.builder()
                        .response("Area no encontrada")
                        .build();
            }
            return new AreaResponse(area.getCode(), area.getName());
        }
    }

}
