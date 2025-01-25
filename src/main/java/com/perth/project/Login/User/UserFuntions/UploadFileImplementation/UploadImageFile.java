package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

public class UploadImageFile {

    public static void InnerUploadImageFile (MultipartFile file, String fileName)   {
        try {
            String fileOriginalName = file.getOriginalFilename();
            if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".png") && !fileOriginalName.endsWith(".jpeg")) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "El archivo debe ser de tipo jpg , png o jpeg");
            }
            byte[] bytes = file.getBytes();
            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;
            File folder = new File("src/main/resources/images");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Path path = Paths.get("src/main/resources/images/" + newFileName);
            Files.write(path,bytes);

        } catch (Exception e) {
            throw new BusinessException(
                BusinessErrorCodes.BAD_REGISTER,
                "Error al subir el archivo");
        }
        }
    
        
}
