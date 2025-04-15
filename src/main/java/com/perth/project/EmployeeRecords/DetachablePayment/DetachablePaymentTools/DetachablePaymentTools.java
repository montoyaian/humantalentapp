package com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools;

import org.springframework.stereotype.Service;

import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePayment;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetachablePaymentTools {
    private final DetachablePaymentRepository detachablePaymentRepository;
    private final UserRepository userRepository;

    public DetachablePayment checkInfo(String id) {
        DetachablePayment detachablePayment = detachablePaymentRepository.findById(id)
                .orElse(null);
        if (detachablePayment == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de pago desprendible no está registrada");
        }
        return detachablePayment;
    }

    public void checkIdentification(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No existe usuario con esa identificación"));
    }
}