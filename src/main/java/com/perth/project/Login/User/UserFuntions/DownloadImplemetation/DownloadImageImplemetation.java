package com.perth.project.Login.User.UserFuntions.DownloadImplemetation;

import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadImageFileSftp;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

public class DownloadImageImplemetation {

    public String handleFiledownload(String fileName, String fileType) {
        try {

            return "Archivo descargado correctamente";
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al descargar el archivo" + e.getMessage());
        }
        
    }
}
