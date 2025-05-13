package com.perth.project.Cv.AcademicSupport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.AcademicSupportRequest;
import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.AcademicSupportResponse;
import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.AcademicSupportTools;
import com.perth.project.Cv.AcademicSupport.AcademicSupportTools.EditAcademicSupport;
import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadFileService;

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
        academicSupportTools.checkIdentification(request.getUser_id());
        String fileName = request.getUser_id() + "_" + request.getAcademicTraining().replace( " ","_");
        AcademicSupport academicSupport = AcademicSupport.builder()
                .userId(request.getUser_id())
                .TypeOfTraining(request.getTypeOfTraining())
                .TypeOfStudy(request.getTypeOfStudy())
                .Institution(request.getInstitution())
                .AcademicTraining(request.getAcademicTraining())
                .Graduate(request.isGraduate())
                .SupportDocument(fileName)
                .build();
        
        uploadFileService.handleFileUpload(file,fileName,"document","academicSupport");
        academicSupportRepository.save(academicSupport);
        return AuthResponse.builder()
                .response("Información de soporte académico creada correctamente")
                .build();
    }

    public AuthResponse editAcademicSupport(String id, EditAcademicSupport request,MultipartFile file) {
        AcademicSupport academicSupport = academicSupportTools.checkInfo(id);
        String RfileName = academicSupport.getSupportDocument();
        if(request != null){
            String NewFileName = academicSupport.getUserId() + "_" + request.getAcademicTraining().replace( " ","_");
            academicSupport.setTypeOfTraining(request.getTypeOfTraining());
            academicSupport.setTypeOfStudy(request.getTypeOfStudy());
            academicSupport.setInstitution(request.getInstitution());
            academicSupport.setAcademicTraining(request.getAcademicTraining());
            academicSupport.setGraduate(request.isGraduate());
            
            if (file == null){
                uploadDocumentFile.renameDocument(academicSupport.getSupportDocument(),NewFileName,"academicSupport");
            }
            academicSupport.setSupportDocument(NewFileName);
            academicSupportRepository.save(academicSupport);
        }
        if (file != null) {
            uploadDocumentFile.deleteDocument(RfileName, "academicSupport");
            uploadFileService.handleFileUpload(file,academicSupport.getSupportDocument(),"document","academicSupport");
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
                            academicSupport.getId(),
                            academicSupport.getTypeOfTraining(),
                            academicSupport.getTypeOfStudy(),
                            academicSupport.getInstitution(),
                            academicSupport.getAcademicTraining(),
                            academicSupport.isGraduate(),
                            academicSupport.getSupportDocument(),
                            academicSupport.getUserId()
                            ))
                    .collect(Collectors.toList());
            return academicSupportResponses;
        } else {
            List<AcademicSupport> academicSupport = academicSupportRepository.findByuserId(id);

            List<AcademicSupportResponse> academicSupportResponses = academicSupport.stream()
                    .map(academicSupports -> new AcademicSupportResponse(
                            academicSupports.getId(),
                            academicSupports.getTypeOfTraining(),
                            academicSupports.getTypeOfStudy(),
                            academicSupports.getInstitution(),
                            academicSupports.getAcademicTraining(),
                            academicSupports.isGraduate(),
                            academicSupports.getSupportDocument(),
                            academicSupports.getUserId()))
                    .collect(Collectors.toList());
            return academicSupportResponses;
        }
    }
}