package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpConection {
    public static FTPClient conecFtpClient(){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(System.getenv("DIRECTION"), 21);
            ftpClient.login(System.getenv("CLOUDUSER"), System.getenv("CLOUDPASSWORD"));
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
