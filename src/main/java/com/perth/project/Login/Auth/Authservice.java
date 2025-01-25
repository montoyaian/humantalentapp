package com.perth.project.Login.Auth;

import javax.mail.MessagingException;
import javax.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Email.EmailFuntions;
import com.perth.project.Login.User.*;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Login.jwt.JwtService;

import lombok.RequiredArgsConstructor;

import com.perth.project.Login.User.UserFuntions.UserFuntions;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;

@Service
@RequiredArgsConstructor
public class Authservice {
    final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserFuntions userFuntions;
    private final UploadFileService uploadFileService;

    @Autowired
    @Qualifier("emailSession")
    private final Session emailSession;

    public LoginResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (!userRepository.isAccountBlocked(request.getUsername())) {
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);

                String token = jwtService.getToken(user);
                String authority = user.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .findFirst()
                        .orElse(null);
                userRepository.unblockUser(request.getUsername());
                return LoginResponse.builder()
                        .response(token)
                        .authority(authority)
                        .build();
            } else {
                throw new BusinessException(
                        BusinessErrorCodes.ACCOUNT_LOCKED,
                        "Demasiados intentos, cuenta bloqueada");

            }
        } catch (Exception e) {
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElse(null);
            if (user != null) {

                userRepository.incrementFailedTrys(request.getUsername());
                Integer failedtrys = userRepository.getFailedTrys(request.getUsername());
                if (failedtrys > 3) {
                    userRepository.blockAccount(request.getUsername());
                    throw new BusinessException(
                            BusinessErrorCodes.ACCOUNT_LOCKED,
                            "Demasiados intentos, cuenta bloqueada");
                }
            }
            throw new BusinessException(
                    BusinessErrorCodes.BAD_CREDENTIALS,
                    "Credenciales invalidas");
        }
    }

    public AuthResponse register(RegisterRequest request, MultipartFile file) {
        String password = UserFuntions.generatePassword();
        User user = User.builder()
                .username(userFuntions.CreateUserName(request.getFirstName(), request.getLastName()))
                .password(passwordEncoder.encode(password))
                .identification(request.getIdentification())
                .profile(request.getProfile())
                .area(request.getArea())
                .email(request.getEmail())
                .failedAttemps(0)
                .blockedAccount(false)
                .role(Role.USER)
                .build();
        AuthResponse validationResponse = userFuntions.Validation(user);
        if (validationResponse != null) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    validationResponse.getResponse());
        }

        String templatePath = EmailFuntions.pathTemplate();
        try {
            UserFuntions.Notification(request.getEmail(), templatePath, user.getUsername(), emailSession, password);
            uploadFileService.handleFileUpload(file, user.getUsername(), "image");
            userRepository.save(user);
            return AuthResponse.builder()
                    .response(jwtService.getToken(user))
                    .build();
        } catch (MessagingException e) {
            throw new BusinessException(
                    BusinessErrorCodes.NO_NOTIFICATION,
                    e.getMessage());
        }
    }
}
