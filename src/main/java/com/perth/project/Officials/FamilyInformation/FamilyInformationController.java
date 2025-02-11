package com.perth.project.Officials.FamilyInformation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.FamilyInformationRequest;
import com.perth.project.Officials.FamilyInformation.FamilyInformationTools.EditFamilyInformation;

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
public class FamilyInformationController {
    private final FamilyInformationService familyInformationService;

    @PostMapping("admin/familyinformation/create")
    public ResponseEntity<AuthResponse> createFamilyInformation(@RequestBody @Valid FamilyInformationRequest request) {
        return ResponseEntity.ok(familyInformationService.createFamilyInformation(request));
    }

    @PutMapping("admin/familyinformation/edit/{id}")
    public ResponseEntity<AuthResponse> editFamilyInformation(@RequestBody @Valid EditFamilyInformation request, @PathVariable("id") String id) {
        return ResponseEntity.ok(familyInformationService.editFamilyInformation(id, request));
    }

    @DeleteMapping("admin/familyinformation/delete/{id}")
    public ResponseEntity<AuthResponse> deleteFamilyInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(familyInformationService.deleteFamilyInformation(id));
    }

    @GetMapping("admin/familyinformation/read/{id}")
    public ResponseEntity<Object> readFamilyInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(familyInformationService.readFamilyInformation(id));
    }
}