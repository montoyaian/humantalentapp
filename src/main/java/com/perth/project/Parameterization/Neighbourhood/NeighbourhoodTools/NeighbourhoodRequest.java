package com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NeighbourhoodRequest {
    @NotNull(message = "El nombre del barrio es obligatorio")
    String name;

    @NotNull(message = "La ubicación del barrio es obligatoria")
    String location;

    @NotNull(message = "La comuna del barrio es obligatoria")
    String commune;
}