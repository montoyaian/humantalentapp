package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import java.io.InputStream;

@Service
public class UploadDocumentFile {

    @Autowired
    public UploadDocumentFile() {
    }

    public void changeDirectory(FTPClient ftpClient, String directory) {
        try {
            ftpClient.changeWorkingDirectory("documents");
            ftpClient.makeDirectory(directory);
            ftpClient.changeWorkingDirectory(directory);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo: " + e.getMessage());
        }
    }

    public void innerUploadDocumentFile(MultipartFile file, String fileName, String fileInfo) {
        String fileOriginalName = file.getOriginalFilename();
        if (!fileOriginalName.endsWith(".pdf")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo pdf");
        }
        FTPClient ftpClient = FtpConnection.conecFtpClient();
        InputStream inputStream = null;
        changeDirectory(ftpClient, fileInfo);
        try {
            inputStream = file.getInputStream();
            ftpClient.storeFile(fileName + ".pdf", inputStream);
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar el archivo");
                }
            }
            FtpConnection.disconnectFtpClient(ftpClient);
        }
    }

    public void deleteDocument(String userName, String fileInfo) {
        FTPClient ftpClient = FtpConnection.conecFtpClient();
        changeDirectory(ftpClient, fileInfo);
        try {
            ftpClient.deleteFile(userName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo: " + e.getMessage());
        } finally {
            FtpConnection.disconnectFtpClient(ftpClient);
        }
    }
}