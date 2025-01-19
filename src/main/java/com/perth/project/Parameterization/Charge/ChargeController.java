package com.perth.project.Parameterization.Charge;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.Charge.ChargeTools.ChargeRequest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ChargeController {
    private final ChargeService ChargeService;

    @PostMapping("admin/charge/create")
    public ResponseEntity<AuthResponse> createCharge(@RequestBody @Valid ChargeRequest request) {
        return ResponseEntity.ok(ChargeService.createCharge(request));
    }

    @PutMapping("admin/charge/edit/{ChargeId}")
    public ResponseEntity<AuthResponse> editCharge(@RequestBody @Valid ChargeRequest request, @PathVariable("ChargeId") Integer ChargeId) {
        return ResponseEntity.ok(ChargeService.editCharge(ChargeId, request));
    }

    @DeleteMapping("admin/charge/delete/{ChargeId}")
    public ResponseEntity<AuthResponse> deleteCharge(@PathVariable("ChargeId") Integer ChargeId) {
        return ResponseEntity.ok(ChargeService.deleteCharge(ChargeId));
    }

    @GetMapping("admin/charge/read/{ChargeId}")
    public ResponseEntity<Object> readCharge(@PathVariable("ChargeId") String ChargeId) {
        return ResponseEntity.ok(ChargeService.readCharge(ChargeId));
    }
}