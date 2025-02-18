package com.perth.project.Login.User.UserFuntions.UploadFileImplementation;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class SshConnection {

    @Bean
    public ChannelSftp sftpClient() {
        ChannelSftp sftpChannel = null;
        try {
            Remote remote = new Remote();
            remote.setUser("humantalent");
            remote.setHost("98.142.103.130");
            remote.setPort(22);
            remote.setIdentity("C:/Users/monto/.ssh/id_rsa");
            remote.setPassphrase(null); // Si tienes una frase de contraseña, configúrala aquí
            remote.setPassword(null); // Si necesitas una contraseña, configúrala aquí

            Session session = getSession(remote);
            session.connect();
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftpChannel;
    }

    public static Session getSession(Remote remote) throws JSchException {
        JSch jSch = new JSch();
        if (Files.exists(Paths.get(remote.getIdentity()))) {
            jSch.addIdentity(remote.getIdentity(), remote.getPassphrase());
        }
        Session session = jSch.getSession(remote.getUser(), remote.getHost(), remote.getPort());
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

    public void disconnectSftpClient(ChannelSftp sftpChannel) {
        if (sftpChannel != null && sftpChannel.isConnected()) {
            try {
                sftpChannel.disconnect();
                sftpChannel.getSession().disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}