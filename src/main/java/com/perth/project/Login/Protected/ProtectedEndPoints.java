package com.perth.project.Login.Protected;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.UnblockUser;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProtectedEndPoints {
    private final UnblockUser unblockUser;

    @PostMapping(value = "home")
    public String welcome() {

        return "security end point";
    }

    @PostMapping("admin/user/unblock/{userName}")
    public ResponseEntity<AuthResponse> unblockAccount(@PathVariable("userName") String userId) {

        return ResponseEntity.ok(unblockUser.Unblock(userId));
    }

}
