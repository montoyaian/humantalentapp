package com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditDetachablePayment {
    @NotNull(message = "El mes es obligatorio")
    String month;

    @NotNull(message = "El año es obligatorio")
    int year;
}