package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpConnection {
    public static FTPClient conecFtpClient(){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("98.142.103.130", 21);
            ftpClient.login("humantalent", "Admin$2025");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftpClient;
    }
    public static void disconnectFtpClient(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}