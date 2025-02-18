package com.perth.project.Parameterization.Neighbourhood;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools.NeighbourhoodRequest;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools.NeighbourhoodResponse;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools.NeighbourhoodFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighbourhoodService {

    private final NeighbourhoodRepository neighbourhoodRepository;
    private final NeighbourhoodFunctions neighbourhoodFunctions;

    public AuthResponse createNeighbourhood(NeighbourhoodRequest request) {
        Neighbourhood neighbourhood = Neighbourhood.builder()
                .name(request.getName())
                .location(request.getLocation())
                .commune(request.getCommune())
                .build();
 
        neighbourhoodRepository.save(neighbourhood);
        return AuthResponse.builder()
                .response("Barrio creado correctamente")
                .build();
    }

    public AuthResponse editNeighbourhood(Integer id, NeighbourhoodRequest request) {
        Neighbourhood neighbourhood = neighbourhoodFunctions.checkNeighbourhood(id);
        neighbourhood.setName(request.getName());
        neighbourhood.setLocation(request.getLocation());
        neighbourhood.setCommune(request.getCommune());

        neighbourhoodRepository.save(neighbourhood);
        return AuthResponse.builder()
                .response("Barrio editado correctamente")
                .build();
    }

    public AuthResponse deleteNeighbourhood(Integer id) {
        Neighbourhood neighbourhood = neighbourhoodFunctions.checkNeighbourhood(id);
        neighbourhoodRepository.delete(neighbourhood);
        return AuthResponse.builder()
                .response("Barrio eliminado correctamente")
                .build();
    }

    public Object readNeighbourhood(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<Neighbourhood> neighbourhoods = neighbourhoodRepository.findAll();
            List<NeighbourhoodResponse> neighbourhoodResponses = neighbourhoods.stream()
                    .map(neighbourhood -> new NeighbourhoodResponse(
                            neighbourhood.getId(),
                            neighbourhood.getName(),
                            neighbourhood.getLocation(),
                            neighbourhood.getCommune()))
                    .collect(Collectors.toList());
            return neighbourhoodResponses;
        } else {
            Neighbourhood neighbourhood = neighbourhoodFunctions.checkNeighbourhood(Integer.valueOf(id));
            if (neighbourhood == null) {
                return AuthResponse.builder()
                        .response("Barrio no encontrado")
                        .build();
            }
            return new NeighbourhoodResponse(
                    neighbourhood.getId(),
                    neighbourhood.getName(),
                    neighbourhood.getLocation(),
                    neighbourhood.getCommune());
        }
    }
}