package com.perth.project.Login.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Parameterization.User.UserFuntions.ResetPassword.ResetPasswordRequest;
import com.perth.project.Parameterization.User.UserFuntions.ResetPassword.ResetPasswordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final Authservice authService;
    private final ResetPasswordService ResetPasswordService;
    @PostMapping(value = "login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }


    @GetMapping(value = "user/resetpasswordnotification/{Email}")
    public ResponseEntity<AuthResponse> ResetPasswordnotification( @PathVariable("Email") String Email) {
        return ResponseEntity.ok(ResetPasswordService.ResetPasswrodNotification(Email));
    }

    @PutMapping(value = "user/resetpassword")
    public ResponseEntity<AuthResponse> ResetPassword(@RequestBody @Valid ResetPasswordRequest request){
        return ResponseEntity.ok(ResetPasswordService.ResetPassword(request.getToken(), request.getPassword()));
    }
}
