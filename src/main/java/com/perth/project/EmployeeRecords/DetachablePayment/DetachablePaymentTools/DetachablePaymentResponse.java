package com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetachablePaymentResponse {
    private String id;
    private String month;
    private int year;
    private String detachable;
}