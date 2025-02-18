package com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeighbourhoodResponse {
    private Integer id;
    private String name;
    private String location;
    private String commune;
}