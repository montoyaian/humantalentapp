package com.perth.project.Cv.LaboralExperience;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.EditLaboralExperience;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.LaboralExperienceRequest;
import com.perth.project.Login.Auth.AuthResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LaboralExperienceController {
    private final LaboralExperienceService laboralExperienceService;

    @PostMapping(value = "admin/laboralexperience/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createLaboralExperience(@RequestPart @Valid LaboralExperienceRequest request,
                                                        @RequestPart("file") MultipartFile file) {      
        return ResponseEntity.ok(laboralExperienceService.createLaboralExperience(request, file));
    }

    @PutMapping(value = "admin/laboralexperience/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editLaboralExperience(@PathVariable("id") String id, 
                                                             @RequestPart(required = false) EditLaboralExperience request,
                                                             @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(laboralExperienceService.editLaboralExperience(id, request,file));
    }   

    @DeleteMapping(value = "admin/laboralexperience/delete/{id}")
    public ResponseEntity<AuthResponse> deleteLaboralExperience(@PathVariable("id") String id) {
        return ResponseEntity.ok(laboralExperienceService.deleteLaboralExperience(id));
    }

    @GetMapping(value = "user/laboralexperience/read/{id}")
    public ResponseEntity<Object> readLaboralExperience(@PathVariable("id") String id) {
        return ResponseEntity.ok(laboralExperienceService.readLaboralExperience(id));
    }
}