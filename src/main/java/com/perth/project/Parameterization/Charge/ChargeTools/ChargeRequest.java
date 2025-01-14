package com.perth.project.Parameterization.Charge.ChargeTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequest {
    @NotNull(message = "El nombre del cargo es obligatorio")
    String name;
}