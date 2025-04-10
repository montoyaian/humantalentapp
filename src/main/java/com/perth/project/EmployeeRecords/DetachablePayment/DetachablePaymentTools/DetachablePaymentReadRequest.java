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
        @NotNull(message = "El nombre es obligatorio")
        String fileName;
    
        @NotNull(message = "El tipo de archivo es obligatorio")
        String fileType;
    
    }
