package com.perth.project.Parameterization.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;

public class SftpConnection {

    private static final String HOST = "98.142.103.130";
    private static final int PORT = 1157;
    private static final String USERNAME = System.getenv("CLOUDUSER");
    private static final String PASSWORD = System.getenv("CLOUDPASSWORD");


    public static ChannelSftp connectSftpChannel() {
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(USERNAME, HOST, PORT);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("PreferredAuthentications", "password");

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Error de conexión SFTP: " + e.getMessage());
        }
        return channelSftp;
    }

    public static void disconnectSftpChannel(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            try {
                Session session = channelSftp.getSession();
                if (channelSftp.isConnected()) {
                    channelSftp.disconnect();
                }
                if (session != null && session.isConnected()) {
                    session.disconnect();
                }
            } catch (Exception e) {
                System.err.println("Error al desconectar SFTP: " + e.getMessage());
            }
        }
    }
}
