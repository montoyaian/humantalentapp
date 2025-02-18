package com.perth.project.EmployeeRecords.CertificateRet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFile;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.CertificateRetRequest;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.CertificateRetResponse;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.CertificateRetTools;
import com.perth.project.EmployeeRecords.CertificateRet.CertificateRetTools.EditCertificateRet;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateRetService {
    
    private final CertificateRetRepository certificateRetRepository;
    private final CertificateRetTools certificateRetTools;
    private final UploadFileService uploadFileService; 
    private final UploadDocumentFile uploadDocumentFile;
    public AuthResponse createCertificateRet(CertificateRetRequest request, MultipartFile file) {
        String UserName = certificateRetTools.checkIdentification(request.getId());
        CertificateRet certificateRet = CertificateRet.builder()
                .ID(request.getId())
                .Year(request.getYear())
                .detachable(UserName)
                .build();
        
        uploadFileService.handleFileUpload(file, UserName, "document", "certificateRet");
        certificateRetRepository.save(certificateRet);
        return AuthResponse.builder()
                .response("Información de certificado creada correctamente")
                .build();
    }

    public AuthResponse editCertificateRet(String id, EditCertificateRet request, MultipartFile file) {
        CertificateRet certificateRet = certificateRetTools.checkInfo(id);
        certificateRet.setYear(request.getYear());
        if (file != null) {
            uploadFileService.handleFileUpload(file, certificateRet.getDetachable(), "document", "certificateRet");
        }
        certificateRetRepository.save(certificateRet);
        return AuthResponse.builder()
                .response("Información de certificado editada correctamente")
                .build();
    }

    public AuthResponse deleteCertificateRet(String id) {
        CertificateRet certificateRet = certificateRetTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(certificateRet.getDetachable(), "certificateRet");
        certificateRetRepository.delete(certificateRet);
        return AuthResponse.builder()
                .response("Información de certificado eliminada correctamente")
                .build();
    }

    public Object readCertificateRet(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<CertificateRet> certificateRets = certificateRetRepository.findAll();
            List<CertificateRetResponse> certificateRetResponses = certificateRets.stream()
                    .map(certificateRet -> new CertificateRetResponse(
                            certificateRet.getID(),
                            certificateRet.getYear(),
                            certificateRet.getDetachable()))
                    .collect(Collectors.toList());
            return certificateRetResponses;
        } else {
            CertificateRet certificateRet = certificateRetTools.checkInfo(id);
            return new CertificateRetResponse(
                    certificateRet.getID(),
                    certificateRet.getYear(),
                    certificateRet.getDetachable());
        }
    }
}