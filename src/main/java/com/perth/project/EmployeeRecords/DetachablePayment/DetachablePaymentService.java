package com.perth.project.EmployeeRecords.DetachablePayment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.UserFuntions.DownloadImplemetation.DownloadDocumentFileSftp;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.DetachablePaymentRequest;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.DetachablePaymentResponse;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.DetachablePaymentTools;
import com.perth.project.EmployeeRecords.DetachablePayment.DetachablePaymentTools.EditDetachablePayment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetachablePaymentService {
    
    private final DetachablePaymentRepository detachablePaymentRepository;
    private final DetachablePaymentTools detachablePaymentTools;
    private final UploadFileService uploadFileService; 
    private final DownloadDocumentFileSftp DownloadDocumentFileSftp;
    private final UploadDocumentFileSftp uploadDocumentFile;
    public AuthResponse createDetachablePayment(DetachablePaymentRequest request, MultipartFile file) {
        detachablePaymentTools.checkIdentification(request.getUser_id());
        DetachablePayment detachablePayment = DetachablePayment.builder()
                .user_id(request.getUser_id())
                .Month(request.getMonth())
                .Year(request.getYear())
                .detachable(request.getUser_id()+"_"+ request.getYear()+"_"+request.getMonth())
                .build();
        
        uploadFileService.handleFileUpload(file, request.getUser_id() +"_"+ request.getYear()+"_"+request.getMonth(), "document", "detachablePayment");
        detachablePaymentRepository.save(detachablePayment);
        return AuthResponse.builder()
                .response("Información de pago desprendible creada correctamente")
                .build();
    }

    public AuthResponse editDetachablePayment(String id, EditDetachablePayment request, MultipartFile file) {
        DetachablePayment detachablePayment = detachablePaymentTools.checkInfo(id);
        if(request!= null){
            detachablePayment.setMonth(request.getMonth());
            detachablePayment.setYear(request.getYear());    
        }
        if (file != null) {
            uploadFileService.handleFileUpload(file, detachablePayment.getDetachable(), "document", "detachablePayment");
        }
        detachablePaymentRepository.save(detachablePayment);
        return AuthResponse.builder()
                .response("Información de pago desprendible editada correctamente")
                .build();
    }

    public AuthResponse deleteDetachablePayment(String id) {
        DetachablePayment detachablePayment = detachablePaymentTools.checkInfo(id);
        uploadDocumentFile.deleteDocument(detachablePayment.getDetachable(), "detachablePayment");
        detachablePaymentRepository.delete(detachablePayment);
        return AuthResponse.builder()
                .response("Información de pago desprendible eliminada correctamente")
                .build();
    }

    public Object readDetachablePayment(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<DetachablePayment> detachablePayments = detachablePaymentRepository.findAll();
            List<DetachablePaymentResponse> detachablePaymentResponses = detachablePayments.stream()
                    .map(detachablePayment -> new DetachablePaymentResponse(
                            detachablePayment.getUser_id(),
                            detachablePayment.getMonth(),
                            detachablePayment.getYear(),
                            detachablePayment.getDetachable()))
                    .collect(Collectors.toList());
            return detachablePaymentResponses;
        } else {
            List<DetachablePayment> detachablePayments = detachablePaymentRepository.findPaymentsByUserId(id);
            if (detachablePayments.isEmpty()) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_CREDENTIALS,
                        "No se encontraron pagos desprendibles para el usuario con ID: " + id);
            }
            return detachablePayments.stream()
                    .map(payment -> new DetachablePaymentResponse(
                            payment.getUser_id(),
                            payment.getMonth(),
                            payment.getYear(),
                            payment.getDetachable()))
                    .collect(Collectors.toList());
        }
    }
    public ResponseEntity<byte[]> downloadDetachablePayment(String fileType,String fileName ,String token) {

        return DownloadDocumentFileSftp.downloadFile(fileName + ".pdf",fileType ,token);
    }

}