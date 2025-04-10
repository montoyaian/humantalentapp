package com.perth.project.Login.User.UserFuntions.DownloadImplemetation;

import com.jcraft.jsch.ChannelSftp;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.SftpConnection;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DownloadDocumentFileSftp {
    private final CheckAut checkAut;

    public ResponseEntity<byte[]> downloadFile(String fileName, String fileType,String token) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();

            UploadDocumentFileSftp.changeDirectory(sftpChannel, "documents", fileType);     
            checkAut.CheckToken(token,fileName);       
            File tempFile = File.createTempFile("sftp-", "-" + fileName);
            try (InputStream inputStream = sftpChannel.get(fileName);
                 FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            byte[] fileContent = java.nio.file.Files.readAllBytes(tempFile.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(fileContent);

        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al descargar el archivo: " + e.getMessage());
        } finally {
            SftpConnection.disconnectSftpChannel(sftpChannel);
        }
    }
}
