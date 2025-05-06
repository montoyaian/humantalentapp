package com.perth.project.Cv.AcademicSupport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.AcademicSupportRequest;
import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.EditAcademicSupport;
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
public class AcademicSupportController {
    private final AcademicSupportService academicSupportService;

    @PostMapping(value = "admin/academicsupport/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createAcademicSupport(@RequestPart @Valid AcademicSupportRequest request,
                                                            @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(academicSupportService.createAcademicSupport(request,file));
    }

    @PutMapping(value ="admin/academicsupport/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editAcademicSupport(@RequestPart(required = false)@Valid EditAcademicSupport request,
                                                     @PathVariable("id") String id,@RequestPart(required = false)  MultipartFile file) {
        return ResponseEntity.ok(academicSupportService.editAcademicSupport(id, request,file));
    }       

    @DeleteMapping(value ="admin/academicsupport/delete/{id}")
    public ResponseEntity<AuthResponse> deleteAcademicSupport(@PathVariable("id") String id) {
        return ResponseEntity.ok(academicSupportService.deleteAcademicSupport(id));
    }

    @GetMapping(value ="user/academicsupport/read/{id}")
    public ResponseEntity<Object> readAcademicSupport(@PathVariable("id") String id) {
        return ResponseEntity.ok(academicSupportService.readAcademicSupport(id));
    }
}