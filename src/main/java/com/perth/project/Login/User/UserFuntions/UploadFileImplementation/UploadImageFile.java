package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import java.io.InputStream;

@Service
public class UploadImageFile {

    private final SshConnection sshConnection;

    @Autowired
    public UploadImageFile(SshConnection sshConnection) {
        this.sshConnection = sshConnection;
    }

    public void  innerUploadImageFile(MultipartFile file, String fileName) {
        String fileOriginalName = file.getOriginalFilename();
        if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".png") && !fileOriginalName.endsWith(".jpeg")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo jpg, png o jpeg");
        }
        ChannelSftp sftpChannel = sshConnection.sftpClient();
        try (InputStream inputStream = file.getInputStream()) {
            sftpChannel.cd("images");
            sftpChannel.put(inputStream, fileName + ".jpg");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo");
        } finally {
            sshConnection.disconnectSftpClient(sftpChannel);
        }
    }

    public void deletePhoto(String userName) {
        ChannelSftp sftpChannel = sshConnection.sftpClient();
        try {
            sftpChannel.cd("images");
            sftpChannel.rm(userName + ".jpg");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo");
        } finally {
            sshConnection.disconnectSftpClient(sftpChannel);
        }
    }
}