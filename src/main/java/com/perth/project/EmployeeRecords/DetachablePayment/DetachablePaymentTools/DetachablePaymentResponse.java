package com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetachablePaymentResponse {
    private String ID;
    private String Month;
    private int Year;
    private String detachable;
}