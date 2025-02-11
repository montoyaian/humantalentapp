package com.perth.project.Officials.PersonalInformation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.EditPersonalInformationRequest;
import com.perth.project.Officials.PersonalInformation.PersonalInformationTools.PersonalInformationRequest;


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
public class PersonalInformationController {
    private final PersonalInformationService personalInformationService;

    @PostMapping("admin/personalinformation/create")
    public ResponseEntity<AuthResponse> createPersonalInformation(@RequestBody @Valid PersonalInformationRequest request) {
        return ResponseEntity.ok(personalInformationService.createPersonalInformation(request));
    }

    @PutMapping("admin/personalinformation/edit/{id}")
    public ResponseEntity<AuthResponse> editPersonalInformation(@RequestBody @Valid EditPersonalInformationRequest request, @PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.editPersonalInformation(id, request));
    }

    @DeleteMapping("admin/personalinformation/delete/{id}")
    public ResponseEntity<AuthResponse> deletePersonalInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.deletePersonalInformation(id));
    }

    @GetMapping("admin/personalinformation/read/{id}")
    public ResponseEntity<Object> readPersonalInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(personalInformationService.readPersonalInformation(id));
    }
}