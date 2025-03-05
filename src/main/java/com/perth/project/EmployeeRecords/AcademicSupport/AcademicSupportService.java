package com.perth.project.EmployeeRecords.AcademicSupport;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools.AcademicSupportRequest;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools.AcademicSupportResponse;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools.AcademicSupportTools;
import com.perth.project.EmployeeRecords.AcademicSupport.AcademicSupportTools.EditAcademicSupport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicSupportService {
    
    private final AcademicSupportRepository academicSupportRepository;
    private final AcademicSupportTools academicSupportTools;
    private final UploadFileService uploadFileService; 
    private final UploadDocumentFileSftp uploadDocumentFile;
    private final UserRepository userRepository;
    public AuthResponse createAcademicSupport(AcademicSupportRequest request,MultipartFile file) {
        String UserName = academicSupportTools.checkIdentification(request.getUser_id());
        AcademicSupport academicSupport = AcademicSupport.builder()
                .user_id(request.getUser_id())
                .TypeOfTraining(request.getTypeOfTraining())
                .TypeOfStudy(request.getTypeOfStudy())
                .Institution(request.getInstitution())
                .AcademicTraining(request.getAcademicTraining())
                .Graduate(request.isGraduate())
                .SupportDocument(UserName)
                .build();
        
        uploadFileService.handleFileUpload(file,UserName,"document","academicSupport");
        academicSupportRepository.save(academicSupport);
        return AuthResponse.builder()
                .response("Información de soporte académico creada correctamente")
                .build();
    }

    public AuthResponse editAcademicSupport(String id, EditAcademicSupport request,MultipartFile file) {
        AcademicSupport academicSupport = academicSupportTools.checkInfo(id);
        if(request != null){
            academicSupport.setTypeOfTraining(request.getTypeOfTraining());
            academicSupport.setTypeOfStudy(request.getTypeOfStudy());
            academicSupport.setInstitution(request.getInstitution());
            academicSupport.setAcademicTraining(request.getAcademicTraining());
            academicSupport.setGraduate(request.isGraduate());
            academicSupportRepository.save(academicSupport);
        }
        User user = userRepository.findById(id).orElse(null);
        if (file != null) {
            uploadFileService.handleFileUpload(file,user.getUsername(),"document","academicSupport");
        }
        
        return AuthResponse.builder()
                .response("Información de soporte académico editada correctamente")
                .build();
    }

    public AuthResponse deleteAcademicSupport(String id) {
        AcademicSupport academicSupport = academicSupportTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(academicSupport.getSupportDocument(),"academicSupport");
        academicSupportRepository.delete(academicSupport);
        return AuthResponse.builder()
                .response("Información de soporte académico eliminada correctamente")
                .build();
    }

    public Object readAcademicSupport(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<AcademicSupport> academicSupports = academicSupportRepository.findAll();
            List<AcademicSupportResponse> academicSupportResponses = academicSupports.stream()
                    .map(academicSupport -> new AcademicSupportResponse(
                            academicSupport.getUser_id(),
                            academicSupport.getTypeOfTraining(),
                            academicSupport.getTypeOfStudy(),
                            academicSupport.getInstitution(),
                            academicSupport.getAcademicTraining(),
                            academicSupport.isGraduate(),
                            academicSupport.getSupportDocument()))
                    .collect(Collectors.toList());
            return academicSupportResponses;
        } else {
            AcademicSupport academicSupport = academicSupportTools.checkInfo(id);
            return new AcademicSupportResponse(
                    academicSupport.getUser_id(),
                    academicSupport.getTypeOfTraining(),
                    academicSupport.getTypeOfStudy(),
                    academicSupport.getInstitution(),
                    academicSupport.getAcademicTraining(),
                    academicSupport.isGraduate(),
                    academicSupport.getSupportDocument());
        }
    }
}