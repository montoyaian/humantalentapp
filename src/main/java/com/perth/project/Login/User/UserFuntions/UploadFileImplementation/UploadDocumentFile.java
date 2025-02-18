package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import java.io.InputStream;

@Service
public class UploadDocumentFile {

    private final SshConnection sshConnection;

    @Autowired
    public UploadDocumentFile(SshConnection sshConnection) {
        this.sshConnection = sshConnection;
    }

    public void changeDirectory(ChannelSftp sftpChannel, String directory) {
        try {
            sftpChannel.cd("documents");
            sftpChannel.mkdir(directory);
            sftpChannel.cd(directory);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo" + e.getMessage());
        }
    }

    public void innerUploadDocumentFile(MultipartFile file, String fileName, String fileInfo) {
        String fileOriginalName = file.getOriginalFilename();
        if (!fileOriginalName.endsWith(".pdf")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo pdf");
        }
        ChannelSftp sftpChannel = sshConnection.sftpClient();
        InputStream inputStream = null;
        changeDirectory(sftpChannel, fileInfo);
        try {
            inputStream = file.getInputStream();
            sftpChannel.put(inputStream, fileName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar el archivo");
                }
            }
            sshConnection.disconnectSftpClient(sftpChannel);
        }
    }

    public void deleteDocument(String userName, String fileInfo) {
        ChannelSftp sftpChannel = sshConnection.sftpClient();
        changeDirectory(sftpChannel, fileInfo);
        try {
            sftpChannel.rm(userName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo, " + e.getMessage());
        } finally {
            sshConnection.disconnectSftpClient(sftpChannel);
        }
    }
}