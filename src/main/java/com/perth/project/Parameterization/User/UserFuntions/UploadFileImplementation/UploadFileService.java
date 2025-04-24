package com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.User.UserFuntions.UploadFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UploadFileService implements UploadFile {

    private final UploadImageFileSftp uploadImageFile;
    private final UploadDocumentFileSftp uploadDocumentFile;

    @Override
    public String handleFileUpload(MultipartFile file, String fileName, String fileType, String fileInfo) {
        try {
            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;
            if (fileSize > maxFileSize) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "El tamaño del archivo excede el limite permitido <= 5MB");
            }

            if ("image".equals(fileType)) {
                uploadImageFile.innerUploadImageFile(file, fileName);
            } else if ("document".equals(fileType)) {
                uploadDocumentFile.innerUploadDocumentFile(file, fileName, fileInfo);
            }
            return "Archivo subido correctamente";
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo" + e.getMessage());
        }
    }
}