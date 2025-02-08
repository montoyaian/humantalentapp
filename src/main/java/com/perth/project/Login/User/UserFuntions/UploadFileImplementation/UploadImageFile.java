package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

public class UploadImageFile {

    public static void InnerUploadImageFile (MultipartFile file, String fileName)   {
        String fileOriginalName = file.getOriginalFilename();
        if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".png") && !fileOriginalName.endsWith(".jpeg")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo jpg , png o jpeg");
        }
        FTPClient ftpClient = FtpConection.conecFtpClient();
    
        try (InputStream inputStream = file.getInputStream()) {
            if (!ftpClient.changeWorkingDirectory("images")) {
                ftpClient.makeDirectory("images");
                ftpClient.changeWorkingDirectory("images");
            }
            boolean done = ftpClient.storeFile(fileName + ".jpg", inputStream);            
            if (!done) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "Error al subir el archivo");
            }
        }catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo");     
        }finally {
            FtpConection.disconnectFtpClient(ftpClient);
        }
    }
    public static void deletePhoto(String UserName) {
        FTPClient ftpClient = FtpConection.conecFtpClient();
        try {
            if (ftpClient.changeWorkingDirectory("images")) {
                boolean deleted = ftpClient.deleteFile(UserName + ".jpg");
                if (!deleted) {
                    throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "Error al eliminar el archivo");
                }
            } else {
                throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Directorio no encontrado");
            }
        } catch (Exception e) {
            throw new BusinessException(
                BusinessErrorCodes.BAD_REGISTER,
                "Error al eliminar el archivo");
        } finally {
            FtpConection.disconnectFtpClient(ftpClient);
        }
     }
        
}
