package com.perth.project.Officials;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.OfficialsTools.EditOfficialsRequest;
import com.perth.project.Officials.OfficialsTools.OfficialsRequest;

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
public class OfficialsController {
    private final OfficialsService personalInformationService;

    @PostMapping("admin/officials/create")
    public ResponseEntity<AuthResponse> createPersonalInformation(@RequestBody @Valid OfficialsRequest request) {
        return ResponseEntity.ok(personalInformationService.createOfficials(request));
    }

    @PutMapping("admin/officials/edit/{id}")
    public ResponseEntity<AuthResponse> editPersonalInformation(@RequestBody @Valid EditOfficialsRequest request, @PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.editOfficials(id, request));
    }

    @DeleteMapping("admin/officials/delete/{id}")
    public ResponseEntity<AuthResponse> deletePersonalInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.deleteOfficials(id));
    }

    @GetMapping("admin/officials/read/{id}")
    public ResponseEntity<Object> readPersonalInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.readOfficials(id));
    }
}