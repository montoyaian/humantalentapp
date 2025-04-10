package com.perth.project.EmployeeRecords.DetachablePayment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.UserFuntions.DownloadImplemetation.DownloadDocumentFileSftp;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.DetachablePaymentRequest;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.EditDetachablePayment;
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
public class DetachablePaymentController {
    private final DetachablePaymentService detachablePaymentService;

    @PostMapping(value = "admin/detachablepayment/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> createDetachablePayment(@RequestPart @Valid DetachablePaymentRequest request,
                                                                @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(detachablePaymentService.createDetachablePayment(request, file));
    }

    @PutMapping(value ="admin/detachablepayment/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editDetachablePayment(@RequestPart(required = false) @Valid EditDetachablePayment request,
                                                              @PathVariable("id") String id, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(detachablePaymentService.editDetachablePayment(id, request, file));
    }

    @DeleteMapping(value ="admin/detachablepayment/delete/{id}")
    public ResponseEntity<AuthResponse> deleteDetachablePayment(@PathVariable("id") String id) {
        return ResponseEntity.ok(detachablePaymentService.deleteDetachablePayment(id));
    }

    @GetMapping(value ="admin/detachablepayment/read/{id}")
    public ResponseEntity<Object> readDetachablePayment(@PathVariable("id") String id) {
        return ResponseEntity.ok(detachablePaymentService.readDetachablePayment(id));
    }

    @GetMapping("user/document/detachablepayment/download/{fileName}/{token}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, @PathVariable String token) {
        return detachablePaymentService.downloadDetachablePayment("detachablePayment",fileName ,token);
    }
}
