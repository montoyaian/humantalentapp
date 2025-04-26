package com.perth.project.Parameterization.User.UserFuntions.DownloadImplemetation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Login.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckAut {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public void CheckToken(String token,String name) {
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
   
            String[] parts = name.split("_");
            String userId = parts[0];  
            if (!userId.equals(user.getID())) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "No tienes permisos para descargar este archivo"
                );
             }
        }
    }
}
