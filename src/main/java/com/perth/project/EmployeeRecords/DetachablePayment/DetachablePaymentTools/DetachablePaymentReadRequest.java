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
    public class DetachablePaymentReadRequest {
        @NotNull(message = "El ID es obligatorio")
        String user_id;
    
        @NotNull(message = "El tipo de archivo es obligatorio")
        String fileType;
    
        @NotNull(message = "El año es obligatorio")
        int year;

        @NotNull(message = "El mes es obligatorio")
        int month;
    }
