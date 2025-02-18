package com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.Neighbourhood.Neighbourhood;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighbourhoodFunctions {
    private final NeighbourhoodRepository neighbourhoodRepository;

    public Neighbourhood checkNeighbourhood(Integer id) {
        Neighbourhood neighbourhood = neighbourhoodRepository.findById(id)
                .orElse(null);

        if (neighbourhood == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "El barrio no está registrado");
        } 
        return neighbourhood;
    }
}