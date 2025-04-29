package com.perth.project.Cv.Disabilities;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Cv.Disabilities.DisabilitiesTools.DisabilitiesRequest;
import com.perth.project.Cv.Disabilities.DisabilitiesTools.EditDisabilities;
import com.perth.project.Login.Auth.AuthResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DisabilitiesController {
    private final DisabilitiesService disabilitiesService;

    @PostMapping(value = "admin/disabilities/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createDisabilities(@RequestPart @Valid DisabilitiesRequest request,
                                                          @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(disabilitiesService.createDisabilities(request, file));
    }

    @PutMapping(value = "admin/disabilities/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editDisabilities(@RequestPart(required = false) @Valid EditDisabilities request,
                                                        @PathVariable("id") String id, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(disabilitiesService.editDisabilities(id, request, file));
    }

    @DeleteMapping(value = "admin/disabilities/delete/{id}")
    public ResponseEntity<AuthResponse> deleteDisabilities(@PathVariable("id") String id) {
        return ResponseEntity.ok(disabilitiesService.deleteDisabilities(id));
    }

    @GetMapping(value = "user/disabilities/read/{id}")
    public ResponseEntity<Object> readDisabilities(@PathVariable("id") String id) {
        return ResponseEntity.ok(disabilitiesService.readDisabilities(id));
    }
}