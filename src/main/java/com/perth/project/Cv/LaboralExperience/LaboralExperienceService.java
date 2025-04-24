package com.perth.project.Cv.LaboralExperience;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.*;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.LaboralExperienceRequest;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.LaboralExperienceResponse;
import com.perth.project.Cv.LaboralExperience.LaboralExperienceTools.EditLaboralExperience;
import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadFileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboralExperienceService {
    
    private final LaboralExperienceRepository laboralExperienceRepository;
    private final LaboralExperienceTools laboralExperienceTools;
    private final UploadFileService uploadFileService; 
    private final UploadDocumentFileSftp uploadDocumentFile;

    public AuthResponse createLaboralExperience(LaboralExperienceRequest request, MultipartFile file) {
        laboralExperienceTools.checkIdentification(request.getUserId());
        LaboralExperience laboralExperience = LaboralExperience.builder()
                .ID(request.getUserId())
                .TypeIdentity(request.getTypeIdentity())
                .CompanyName(request.getCompanyName())
                .Charge(request.getCharge())
                .Vinculation(request.getVinculation())
                .TimeService(request.getTimeService())
                .SupportDocument(request.getUserId())
                .build();
        
        uploadFileService.handleFileUpload(file, request.getUserId(), "document", "laboralExperience");
        laboralExperienceRepository.save(laboralExperience);
        return AuthResponse.builder()
                .response("Información de experiencia laboral creada correctamente")
                .build();
    }

    public AuthResponse editLaboralExperience(String id, EditLaboralExperience request, MultipartFile file) {
        LaboralExperience laboralExperience = laboralExperienceTools.checkInfo(id);
        if (request != null) {
            laboralExperience.setTypeIdentity(request.getTypeIdentity());
            laboralExperience.setCompanyName(request.getCompanyName());
            laboralExperience.setCharge(request.getCharge());
            laboralExperience.setVinculation(request.getVinculation());
            laboralExperience.setTimeService(request.getTimeService());
            laboralExperienceRepository.save(laboralExperience);
        }
        if (file != null) {
            uploadDocumentFile.deleteDocument(laboralExperience.getSupportDocument(), "laboralExperience");
            uploadFileService.handleFileUpload(file, laboralExperience.getSupportDocument(), "document", "laboralExperience");
        }
        
        return AuthResponse.builder()
                .response("Información de experiencia laboral editada correctamente")
                .build();
    }

    public AuthResponse deleteLaboralExperience(String id) {
        LaboralExperience laboralExperience = laboralExperienceTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(laboralExperience.getSupportDocument(), "laboralExperience");
        laboralExperienceRepository.delete(laboralExperience);
        return AuthResponse.builder()
                .response("Información de experiencia laboral eliminada correctamente")
                .build();
    }

    public Object readLaboralExperience(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<LaboralExperience> laboralExperiences = laboralExperienceRepository.findAll();
            List<LaboralExperienceResponse> laboralExperienceResponses = laboralExperiences.stream()
                    .map(laboralExperience -> new LaboralExperienceResponse(
                            laboralExperience.getID(),
                            laboralExperience.getTypeIdentity(),
                            laboralExperience.getCompanyName(),
                            laboralExperience.getCharge(),
                            laboralExperience.getVinculation(),
                            laboralExperience.getTimeService(),
                            laboralExperience.getSupportDocument()))
                    .collect(Collectors.toList());
            return laboralExperienceResponses;
        } else {
            LaboralExperience laboralExperience = laboralExperienceTools.checkInfo(id);
            return new LaboralExperienceResponse(
                    laboralExperience.getID(),
                    laboralExperience.getTypeIdentity(),
                    laboralExperience.getCompanyName(),
                    laboralExperience.getCharge(),
                    laboralExperience.getVinculation(),
                    laboralExperience.getTimeService(),
                    laboralExperience.getSupportDocument());
        }
    }
}