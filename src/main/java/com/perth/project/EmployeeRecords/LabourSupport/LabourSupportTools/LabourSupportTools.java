package com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupport;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabourSupportTools {
    private final LabourSupportRepository labourSupportRepository;
    private final UserRepository userRepository;

    public LabourSupport checkInfo(String id) {
        LabourSupport labourSupport = labourSupportRepository.findById(id)
                .orElse(null);
        if (labourSupport == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de soporte laboral no está registrada");
        }
        return labourSupport;
    }

    public String checkIdentification(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));

        if (labourSupportRepository.findById(id).isPresent()) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de soporte laboral ya existe");
        }

        return user.getUsername();
    }
}