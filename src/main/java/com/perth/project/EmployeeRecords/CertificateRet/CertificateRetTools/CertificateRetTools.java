package com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRet;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateRetTools {
    private final CertificateRetRepository certificateRetRepository;
    private final UserRepository userRepository;

    public CertificateRet checkInfo(String id) {
        CertificateRet certificateRet = certificateRetRepository.findById(id)
                .orElse(null);
        if (certificateRet == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información del certificado no está registrada");
        }
        return certificateRet;
    }

    public String checkIdentification(String id) {
        User user = userRepository.findByIdentification(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));

        if (certificateRetRepository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información del certificado ya existe");
        }

        return user.getUsername();
    }
}