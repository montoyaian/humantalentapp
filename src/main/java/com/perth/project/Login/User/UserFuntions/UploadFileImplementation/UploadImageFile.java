package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import java.io.InputStream;

@Service
public class UploadImageFile {

    public void innerUploadImageFile(MultipartFile file, String fileName) {
        String fileOriginalName = file.getOriginalFilename();
        if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".png") && !fileOriginalName.endsWith(".jpeg")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo jpg, png o jpeg");
        }

        FTPClient ftpClient = FtpConnection.conecFtpClient();
        try (InputStream inputStream = file.getInputStream()) {
            ftpClient.changeWorkingDirectory("images");
            ftpClient.storeFile(fileName + ".jpg", inputStream);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo");
        } finally {
            FtpConnection.disconnectFtpClient(ftpClient);
        }
    }

    public void deletePhoto(String userName) {
        FTPClient ftpClient = FtpConnection.conecFtpClient();
        try {
            ftpClient.changeWorkingDirectory("images");
            ftpClient.deleteFile(userName + ".jpg");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo");
        } finally {
            FtpConnection.disconnectFtpClient(ftpClient);
        }
    }
}