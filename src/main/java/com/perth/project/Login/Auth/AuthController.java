package com.perth.project.Login.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.User.UserFuntions.ResetPassword.ResetPasswordRequest;
import com.perth.project.Login.User.UserFuntions.ResetPassword.ResetPasswordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
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

    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> register(@RequestPart @Valid RegisterRequest request,@RequestPart("file") MultipartFile file) {

        return ResponseEntity.ok(authService.register(request,file));
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
