package com.perth.project.Officials.LaboralInformation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.EditLaboralInformationRequest;
import com.perth.project.Officials.LaboralInformation.LaboralInformationTools.LaboralInformationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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
public class LaboralInformationController {
    private final LaboralInformationService laboralInformationService;

    @PostMapping("admin/laboralinformation/create")
    public ResponseEntity<AuthResponse> createLaboralInformation(@RequestBody @Valid LaboralInformationRequest request) {
        return ResponseEntity.ok(laboralInformationService.createLaboralInformation(request));
    }

    @PutMapping("admin/laboralinformation/edit/{id}")
    public ResponseEntity<AuthResponse> editLaboralInformation(@RequestBody @Valid EditLaboralInformationRequest request, @PathVariable("id") String id) {
        return ResponseEntity.ok(laboralInformationService.editLaboralInformation(id, request));
    }

    @DeleteMapping("admin/laboralinformation/delete/{id}")
    public ResponseEntity<AuthResponse> deleteLaboralInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(laboralInformationService.deleteLaboralInformation(id));
    }

    @GetMapping("admin/laboralinformation/read/{id}")
    public ResponseEntity<Object> readLaboralInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(laboralInformationService.readLaboralInformation(id));
    }
}