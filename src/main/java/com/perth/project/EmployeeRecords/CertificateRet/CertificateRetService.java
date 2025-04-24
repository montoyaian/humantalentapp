package com.perth.project.EmployeeRecords.CertificateRet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadFileService;
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
    private final UploadDocumentFileSftp uploadDocumentFile;
    public AuthResponse createCertificateRet(CertificateRetRequest request, MultipartFile file) {
        certificateRetTools.checkIdentification(request.getUser_id());
        CertificateRet certificateRet = CertificateRet.builder()
                .user_id(request.getUser_id())
                .Year(request.getYear())
                .detachable(request.getUser_id()+"_"+request.getYear())
                .build();
        
        uploadFileService.handleFileUpload(file, certificateRet.getDetachable(), "document", "certificateRet");
        certificateRetRepository.save(certificateRet);
        return AuthResponse.builder()
                .response("Información de certificado creada correctamente")
                .build();
    }

    public AuthResponse editCertificateRet(String fileName, EditCertificateRet request, MultipartFile file) {
        CertificateRet certificateRet = certificateRetTools.checkInfo(fileName);
        String RfileName = fileName;
    
        if (request != null) {
            String NewFileName = certificateRet.getUser_id() + "_" + request.getYear();
            String id = fileName.split("_")[0];
            RfileName = NewFileName;
    
            certificateRetRepository.delete(certificateRet);
    
            CertificateRet NewCertificateRet = CertificateRet.builder()
                    .user_id(id)
                    .Year(request.getYear())
                    .detachable(NewFileName)
                    .build();
    
            if (file == null) {
                uploadDocumentFile.renameDocument(fileName, NewFileName, "certificateRet");
            }
    
            certificateRetRepository.save(NewCertificateRet);
        }
    
        if (file != null) {
            uploadDocumentFile.deleteDocument(fileName, "certificateRet");
            uploadFileService.handleFileUpload(file, RfileName, "document", "certificateRet");
        }
    
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
                            certificateRet.getUser_id(),
                            certificateRet.getYear(),
                            certificateRet.getDetachable()))
                    .collect(Collectors.toList());
            return certificateRetResponses;
        } else {
            CertificateRet certificateRet = certificateRetTools.checkInfo(id);
            return new CertificateRetResponse(
                    certificateRet.getUser_id(),
                    certificateRet.getYear(),
                    certificateRet.getDetachable());
        }
    }

    
}