package com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCertificateRet {

    @NotNull(message = "El año es obligatorio")
    int year;
}