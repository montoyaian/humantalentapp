package com.perth.project.Login.Email;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class EmailFuntions {
    public static String replaceValues(String userName, String Password, String template) {

        return template
                .replace("nuevo_usuario", userName)
                .replace("contrasena_usuario", Password);
    }

    public static String pathTemplate() {
        try {
            ClassPathResource resource = new ClassPathResource("templates/Email.html");
            Path tempFile = Files.createTempFile("email-template", ".html");
            Files.copy(resource.getInputStream(), tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            return tempFile.toAbsolutePath().toString();
        } catch (IOException e) {
            return ("Error al obtener el recurso del classpath: " + e.getMessage());
        }
    }

    public static String readTemplate(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            return content;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo HTML: " + e.getMessage());
            return content;
        }
    }
}
