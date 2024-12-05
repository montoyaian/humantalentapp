package com.perth.project.Login.Email;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

public class EmailFuntions {
    public static String replaceValues(String userName, String Password, String template) {

        return template
                .replace("nuevo_usuario", userName)
                .replace("contrasena_usuario", Password);
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
