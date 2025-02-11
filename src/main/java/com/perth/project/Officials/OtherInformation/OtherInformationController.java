package com.perth.project.Officials.OtherInformation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.OtherInformationRequest;
import com.perth.project.Officials.OtherInformation.OtherInformationTools.EditOtherInformation;

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
public class OtherInformationController {
    private final OtherInformationService otherInformationService;

    @PostMapping("admin/otherinformation/create")
    public ResponseEntity<AuthResponse> createOtherInformation(@RequestBody @Valid OtherInformationRequest request) {
        return ResponseEntity.ok(otherInformationService.createOtherInformation(request));
    }

    @PutMapping("admin/otherinformation/edit/{id}")
    public ResponseEntity<AuthResponse> editOtherInformation(@RequestBody @Valid EditOtherInformation request, @PathVariable("id") String id) {
        return ResponseEntity.ok(otherInformationService.editOtherInformation(id, request));
    }

    @DeleteMapping("admin/otherinformation/delete/{id}")
    public ResponseEntity<AuthResponse> deleteOtherInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(otherInformationService.deleteOtherInformation(id));
    }

    @GetMapping("admin/otherinformation/read/{id}")
    public ResponseEntity<Object> readOtherInformation(@PathVariable("id") String id) {
        return ResponseEntity.ok(otherInformationService.readOtherInformation(id));
    }
}