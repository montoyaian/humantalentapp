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
        Disabilities disabilities = Disabilities.builder()
                .userId(request.getUserId())
                .typeOfDisease(request.getTypeOfDisease())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .daysOfIncapacity(daysOfIncapacity)
                .eps(request.getEps())
                .supportDocument(request.getUserId())
                .build();

        uploadFileService.handleFileUpload(file, request.getUserId(), "document", "disabilities");
        disabilitiesRepository.save(disabilities);
        return AuthResponse.builder()
                .response("Información de discapacidad creada correctamente")
                .build();
    }

    public AuthResponse editDisabilities(String id, EditDisabilities request, MultipartFile file) {
        Disabilities disabilities = disabilitiesTools.checkInfo(id);
        if (request != null) {
            disabilities.setTypeOfDisease(request.getTypeOfDisease());
            disabilities.setStartDate(request.getStartDate());
            disabilities.setEndDate(request.getEndDate());
            int daysOfIncapacity = (int) ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
            disabilities.setDaysOfIncapacity(daysOfIncapacity);
            disabilities.setEps(request.getEps());
            disabilitiesRepository.save(disabilities);
        }
        
        if (file != null) {
            uploadDocumentFile.deleteDocument(disabilities.getSupportDocument(), "disabilities");
            uploadFileService.handleFileUpload(file, disabilities.getUserId(), "document", "disabilities");
        }

        return AuthResponse.builder()
                .response("Información de discapacidad editada correctamente")
                .build();
    }

    public AuthResponse deleteDisabilities(String id) {
        Disabilities disabilities = disabilitiesTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(disabilities.getSupportDocument(), "disabilities");
        disabilitiesRepository.delete(disabilities);
        return AuthResponse.builder()
                .response("Información de discapacidad eliminada correctamente")
                .build();
    }

    public Object readDisabilities(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<Disabilities> disabilitiesList = disabilitiesRepository.findAll();
            return disabilitiesList.stream()
                    .map(disability -> new DisabilitiesResponse(
                            disability.getUserId(),
                            disability.getTypeOfDisease(),
                            disability.getStartDate(),
                            disability.getEndDate(),
                            disability.getDaysOfIncapacity(),
                            disability.getEps(),
                            disability.getSupportDocument()))
                    .collect(Collectors.toList());
        } else {
            Disabilities disability = disabilitiesTools.checkInfo(id);
            return new DisabilitiesResponse(
                    disability.getUserId(),
                    disability.getTypeOfDisease(),
                    disability.getStartDate(),
                    disability.getEndDate(),
                    disability.getDaysOfIncapacity(),
                    disability.getEps(),
                    disability.getSupportDocument());
        }
    }
}