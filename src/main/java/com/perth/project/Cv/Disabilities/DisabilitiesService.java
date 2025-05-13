package com.perth.project.Cv.Disabilities;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Cv.Disabilities.DisabilitiesTools.DisabilitiesRequest;
import com.perth.project.Cv.Disabilities.DisabilitiesTools.DisabilitiesResponse;
import com.perth.project.Cv.Disabilities.DisabilitiesTools.DisabilitiesTools;
import com.perth.project.Cv.Disabilities.DisabilitiesTools.EditDisabilities;
import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation.UploadFileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisabilitiesService {

    private final DisabilitiesRepository disabilitiesRepository;
    private final DisabilitiesTools disabilitiesTools;
    private final UploadFileService uploadFileService;
    private final UploadDocumentFileSftp uploadDocumentFile;

    public AuthResponse createDisabilities(DisabilitiesRequest request, MultipartFile file) {
        disabilitiesTools.checkIdentification(request.getUserId());
        int daysOfIncapacity = (int) ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        String fileName = request.getUserId()+"_"+request.getTypeOfDisease().replace( " ","_")+"_"+request.getStartDate();
        Disabilities disabilities = Disabilities.builder()
                .userId(request.getUserId())
                .typeOfDisease(request.getTypeOfDisease())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .daysOfIncapacity(daysOfIncapacity)
                .eps(request.getEps())
                .supportDocument(fileName)
                .build();

        uploadFileService.handleFileUpload(file, fileName, "document", "disabilities");
        disabilitiesRepository.save(disabilities);
        return AuthResponse.builder()
                .response("Información de discapacidad creada correctamente")
                .build();
    }

    public AuthResponse editDisabilities(String fileName, EditDisabilities request, MultipartFile file) {
        Disabilities disabilities = disabilitiesTools.checkInfo(fileName);
        String RfileName = fileName;
        if (request != null) {
            String newFileName = disabilities.getUserId()+"_"+request.getTypeOfDisease().replace( " ","_")+"_"+request.getStartDate();
            RfileName = newFileName;
            disabilities.setTypeOfDisease(request.getTypeOfDisease());
            disabilities.setStartDate(request.getStartDate());
            disabilities.setEndDate(request.getEndDate());
            int daysOfIncapacity = (int) ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
            disabilities.setDaysOfIncapacity(daysOfIncapacity);
            disabilities.setEps(request.getEps());
            disabilities.setSupportDocument(newFileName);
            
            if (file == null) {
                uploadDocumentFile.renameDocument(fileName, newFileName, "disabilities");
            }
            disabilitiesRepository.save(disabilities);
        }
        
        if (file != null) {
            uploadDocumentFile.deleteDocument(fileName, "disabilities");
            uploadFileService.handleFileUpload(file, RfileName, "document", "disabilities");
        }

        return AuthResponse.builder()
                .response("Información de discapacidad editada correctamente")
                .build();
    }

    public AuthResponse deleteDisabilities(String fileName) {
        Disabilities disabilities = disabilitiesTools.checkInfo(fileName);
        uploadDocumentFile.deleteDocument(disabilities.getSupportDocument(), "disabilities");
        disabilitiesRepository.delete(disabilities);
        return AuthResponse.builder()
                .response("Información de discapacidad eliminada correctamente")
                .build();
    }

    public Object readDisabilities(String id) {
        List<DisabilitiesResponse> disabilityResponses;
        if ("all".equalsIgnoreCase(id)) {
            List<Disabilities> disabilitiesList = disabilitiesRepository.findAll();
            disabilityResponses = disabilitiesList.stream()
                    .map(disability -> new DisabilitiesResponse(
                            disability.getId(),
                            disability.getTypeOfDisease(),
                            disability.getStartDate(),
                            disability.getEndDate(),
                            disability.getDaysOfIncapacity(),
                            disability.getEps(),
                            disability.getSupportDocument(),
                            disability.getUserId()))
                    .collect(Collectors.toList());
        } else {
            List<Disabilities> disabilityList = disabilitiesRepository.findByUserId(id);
            disabilityResponses = disabilityList.stream()
                    .map(disability -> new DisabilitiesResponse(
                            disability.getId(),
                            disability.getTypeOfDisease(),
                            disability.getStartDate(),
                            disability.getEndDate(),
                            disability.getDaysOfIncapacity(),
                            disability.getEps(),
                            disability.getSupportDocument(),
                            disability.getUserId()))
                    .collect(Collectors.toList());
        }
        return disabilityResponses;
    }
}