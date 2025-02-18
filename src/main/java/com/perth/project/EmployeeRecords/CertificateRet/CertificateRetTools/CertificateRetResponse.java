package com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateRetResponse {
    private String ID;
    private int Year;
    private String detachable;
}
