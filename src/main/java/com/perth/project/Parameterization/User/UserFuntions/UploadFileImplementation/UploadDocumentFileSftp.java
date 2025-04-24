package com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.ChannelSftp;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class UploadDocumentFileSftp {

    public static void changeDirectory(ChannelSftp sftpChannel, String filetype, String directory) {
        changeOrCreateDirectory(sftpChannel, "public_html");
        changeOrCreateDirectory(sftpChannel, filetype);
        
        if (directory != null) {
            changeOrCreateDirectory(sftpChannel, directory);
        }
    }
    
    private static void changeOrCreateDirectory(ChannelSftp sftpChannel, String directory) {
        try {
            sftpChannel.cd(directory);
        } catch (Exception e) {
            try {
                sftpChannel.mkdir(directory);
                sftpChannel.cd(directory);
            } catch (Exception ex) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "Error al acceder o crear el directorio '" + directory + "': " + ex.getMessage());
            }
        }
    }

    public void innerUploadDocumentFile(MultipartFile file, String fileName, String fileInfo) {
        String fileOriginalName = file.getOriginalFilename();
        if (fileOriginalName == null || !fileOriginalName.endsWith(".pdf")) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "El archivo debe ser de tipo pdf");
        }

        ChannelSftp sftpChannel = null;
        InputStream inputStream = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();
            changeDirectory(sftpChannel,"documents" ,fileInfo);
            inputStream = file.getInputStream();
            sftpChannel.put(inputStream, fileName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al subir el archivo: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }

    public void deleteDocument(String userName, String fileInfo) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();
            changeDirectory(sftpChannel,"documents" ,fileInfo);
            sftpChannel.rm(userName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al eliminar el archivo: " + e.getMessage());
        } finally {
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }

    public void renameDocument(String currentFileName, String newFileName, String fileInfo) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();
    
            changeDirectory(sftpChannel, "documents", fileInfo);
    
            sftpChannel.rename(currentFileName + ".pdf", newFileName + ".pdf");
        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al renombrar el archivo: " + e.getMessage());
        } finally {
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }
}
