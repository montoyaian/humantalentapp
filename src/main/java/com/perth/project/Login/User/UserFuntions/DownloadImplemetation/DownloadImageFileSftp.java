package com.perth.project.Login.User.UserFuntions.DownloadImplemetation;

import java.io.InputStream;
import com.jcraft.jsch.ChannelSftp;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.SftpConnection;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadDocumentFileSftp;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DownloadImageFileSftp {

    public ResponseEntity<ByteArrayResource> downloadImageFile(String fileName) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = SftpConnection.connectSftpChannel();

            UploadDocumentFileSftp.changeDirectory(sftpChannel, "images", null);

            InputStream inputStream = sftpChannel.get(fileName + ".jpg");

            if (inputStream == null) {
                throw new BusinessException(
                        BusinessErrorCodes.BAD_REGISTER,
                        "El archivo no se encontró en el servidor SFTP."
                );
            }

            byte[] fileBytes = inputStream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".jpg\"");


            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new ByteArrayResource(fileBytes));

        } catch (Exception e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error al descargar el archivo: " + e.getMessage()
            );
        } finally {
            if (sftpChannel != null) {
                SftpConnection.disconnectSftpChannel(sftpChannel);
            }
        }
    }
}
