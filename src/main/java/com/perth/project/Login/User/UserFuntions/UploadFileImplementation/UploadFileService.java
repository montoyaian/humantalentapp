package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import com.perth.project.Login.User.UserFuntions.UploadFile;

@Service
public class UploadFileService implements UploadFile{

    @Override
    public String handleFileUpload(MultipartFile file, String fileName, String fileType)  {
        try {
            
            long fileSize = file.getSize();
            long maxFileSize = 5*1024*1024;
            if(fileSize > maxFileSize){
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "El tamaño del archivo excede el limite permitido <= 5MB");
            }

            if (fileType == "image"){
                UploadImageFile.InnerUploadImageFile(file, fileName);
            }
            return "Archivo subido correctamente";
        } catch (Exception e) {
            throw new BusinessException(
                BusinessErrorCodes.BAD_REGISTER,
                "Error al subir el archivo");
        }
    }

  

}
