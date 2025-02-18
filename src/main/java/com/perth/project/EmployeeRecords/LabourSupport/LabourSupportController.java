package com.perth.project.EmployeeRecords.LabourSupport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportRequest;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.EditLabourSupport;

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
public class LabourSupportController {
    private final LabourSupportService labourSupportService;

    @PostMapping(value = "admin/laboursupport/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createLabourSupport(@RequestPart @Valid LabourSupportRequest request,
                                                            @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(labourSupportService.createLabourSupport(request, file));
    }

    @PutMapping(value ="admin/laboursupport/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editLabourSupport(@RequestPart(required = false) @Valid EditLabourSupport request,
                                                          @PathVariable("id") String id, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(labourSupportService.editLabourSupport(id, request, file));
    }

    @DeleteMapping(value ="admin/laboursupport/delete/{id}")
    public ResponseEntity<AuthResponse> deleteLabourSupport(@PathVariable("id") String id) {
        return ResponseEntity.ok(labourSupportService.deleteLabourSupport(id));
    }

    @GetMapping(value ="admin/laboursupport/read/{id}")
    public ResponseEntity<Object> readLabourSupport(@PathVariable("id") String id) {
        return ResponseEntity.ok(labourSupportService.readLabourSupport(id));
    }
}