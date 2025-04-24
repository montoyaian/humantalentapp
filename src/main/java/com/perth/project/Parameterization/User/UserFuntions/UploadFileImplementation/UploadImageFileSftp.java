package com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.ChannelSftp;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

@Service
public class UploadImageFileSftp {

    public  void innerUploadImageFile(MultipartFile file, String fileName) {
        String fileOriginalName = file.getOriginalFilename();
        if (fileOriginalName == null || 
            !(fileOriginalName.endsWith(".jpg") || fileOriginalName.endsWith(".png") || fileOriginalName.endsWith(".jpeg"))) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo jpg, png o jpeg");
        }

        ChannelSftp sftpChannel = null;
        
        try {
            sftpChannel = SftpConnection.connectSftpChannel();
            UploadDocumentFileSftp.changeDirectory(sftpChannel,"images",null);
            try (InputStream inputStream = file.getInputStream()) {
                sftpChannel.put(inputStream, fileName + ".jpg");
            }
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo: " + e.getMessage());
        } finally {
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }

    public void deletePhoto(String userName) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();
            UploadDocumentFileSftp.changeDirectory(sftpChannel, "images", null);
            sftpChannel.rm(userName + ".jpg");


            
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo: " + e.getMessage());
        } finally {
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }
}
