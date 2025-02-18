package com.perth.project.EmployeeRecords.LabourSupport;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFile;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportRequest;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportResponse;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportTools;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.EditLabourSupport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabourSupportService {
    
    private final LabourSupportRepository labourSupportRepository;
    private final LabourSupportTools labourSupportTools;
    private final UploadFileService uploadFileService; 
    private final UploadDocumentFile uploadDocumentFile;
    public AuthResponse createLabourSupport(LabourSupportRequest request, MultipartFile file) {
        String UserName = labourSupportTools.checkIdentification(request.getId());
        LabourSupport labourSupport = LabourSupport.builder()
                .ID(request.getId())
                .TypeIdentity(request.getTypeIdentity())
                .CompanyName(request.getCompanyName())
                .Charge(request.getCharge())
                .Vinculation(request.getVinculation())
                .TimeService(request.getTimeService())
                .SupportDocument(UserName)
                .build();
        
        uploadFileService.handleFileUpload(file, UserName, "document", "labourSupport");
        labourSupportRepository.save(labourSupport);
        return AuthResponse.builder()
                .response("Información de soporte laboral creada correctamente")
                .build();
    }

    public AuthResponse editLabourSupport(String id, EditLabourSupport request, MultipartFile file) {
        LabourSupport labourSupport = labourSupportTools.checkInfo(id);
        labourSupport.setTypeIdentity(request.getTypeIdentity());
        labourSupport.setCompanyName(request.getCompanyName());
        labourSupport.setCharge(request.getCharge());
        labourSupport.setVinculation(request.getVinculation());
        labourSupport.setTimeService(request.getTimeService());
        if (file != null) {
            uploadFileService.handleFileUpload(file, labourSupport.getSupportDocument(), "document", "labourSupport");
        }
        labourSupportRepository.save(labourSupport);
        return AuthResponse.builder()
                .response("Información de soporte laboral editada correctamente")
                .build();
    }

    public AuthResponse deleteLabourSupport(String id) {
        LabourSupport labourSupport = labourSupportTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(labourSupport.getSupportDocument(), "labourSupport");
        labourSupportRepository.delete(labourSupport);
        return AuthResponse.builder()
                .response("Información de soporte laboral eliminada correctamente")
                .build();
    }

    public Object readLabourSupport(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<LabourSupport> labourSupports = labourSupportRepository.findAll();
            List<LabourSupportResponse> labourSupportResponses = labourSupports.stream()
                    .map(labourSupport -> new LabourSupportResponse(
                            labourSupport.getID(),
                            labourSupport.getTypeIdentity(),
                            labourSupport.getCompanyName(),
                            labourSupport.getCharge(),
                            labourSupport.getVinculation(),
                            labourSupport.getTimeService(),
                            labourSupport.getSupportDocument()))
                    .collect(Collectors.toList());
            return labourSupportResponses;
        } else {
            LabourSupport labourSupport = labourSupportTools.checkInfo(id);
            return new LabourSupportResponse(
                    labourSupport.getID(),
                    labourSupport.getTypeIdentity(),
                    labourSupport.getCompanyName(),
                    labourSupport.getCharge(),
                    labourSupport.getVinculation(),
                    labourSupport.getTimeService(),
                    labourSupport.getSupportDocument());
        }
    }
}