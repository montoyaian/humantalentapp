package com.perth.project.EmployeeRecords.CertificateRet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.User.UserFuntions.DownloadImplemetation.DownloadDocumentFileSftp;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.CertificateRetRequest;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.EditCertificateRet;

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
public class CertificateRetController {
    private final CertificateRetService certificateRetService;

    @PostMapping(value = "admin/certificateret/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createCertificateRet(@RequestPart @Valid CertificateRetRequest request,
                                                             @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(certificateRetService.createCertificateRet(request, file));
    }

    @PutMapping(value ="admin/certificateret/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editCertificateRet(@RequestPart(required = false) @Valid EditCertificateRet request,
                                                           @PathVariable("id") String id, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(certificateRetService.editCertificateRet(id, request, file));
    }

    @DeleteMapping(value ="admin/certificateret/delete/{id}")
    public ResponseEntity<AuthResponse> deleteCertificateRet(@PathVariable("id") String id) {
        return ResponseEntity.ok(certificateRetService.deleteCertificateRet(id));
    }

    @GetMapping(value ="user/certificateret/read/{id}")
    public ResponseEntity<Object> readCertificateRet(@PathVariable("id") String id) {
        return ResponseEntity.ok(certificateRetService.readCertificateRet(id));
    }


}