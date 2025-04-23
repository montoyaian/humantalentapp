package com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Login.jwt.JwtService;
import com.perth.project.Officials.OfficialsRepository;
import com.perth.project.Officials.Officials;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabourSupportTools {
    private final  OfficialsRepository officialsRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Officials checkInfo(String id) {

        Officials labourSupport = officialsRepository.findById(id)
                .orElse(null);
        if (labourSupport == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "La información de soporte laboral no está registrada");
        }
        return labourSupport;
    }

    public void checkToken(String token,String id) {
        if (token == null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Token no encontrado"
            );
        }
        String username = jwtService.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).orElse(null);
        String authority = user.getAuthorities().stream()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .findFirst()
            .orElse(null);

        if (!authority.equals("ADMIN")) {

            if (!user.getID().equals(id)) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "No tienes permisos para acceder a esta información"
                );
            }
        }
    
    }
}