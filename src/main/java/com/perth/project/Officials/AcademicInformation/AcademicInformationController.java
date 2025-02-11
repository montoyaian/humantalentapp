package com.perth.project.Officials.AcademicInformation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.AcademicInformationRequest;
import com.perth.project.Officials.AcademicInformation.AcademicInformationTools.EditAcademicInformation;

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
public class AcademicInformationController {
    private final AcademicInformationService academicInformationService;

    @PostMapping("admin/academicinformation/create")
    public ResponseEntity<AuthResponse> createAcademicInformation(@RequestBody @Valid AcademicInformationRequest request) {
        return ResponseEntity.ok(academicInformationService.createAcademicInformation(request));
    }

    @PutMapping("admin/academicinformation/edit/{id}")
    public ResponseEntity<AuthResponse> editAcademicInformation(@RequestBody @Valid EditAcademicInformation request, @PathVariable("id") String id) {
        return ResponseEntity.ok(academicInformationService.editAcademicInformation(id, request));
    }

    @DeleteMapping("admin/academicinformation/delete/{id}")
    public ResponseEntity<AuthResponse> deleteAcademicInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(academicInformationService.deleteAcademicInformation(id));
    }

    @GetMapping("admin/academicinformation/read/{id}")
    public ResponseEntity<Object> readAcademicInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(academicInformationService.readAcademicInformation(id));
    }
}