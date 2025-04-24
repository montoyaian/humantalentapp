package com.perth.project.Parameterization.User.UserFuntions.Notification;

public class TemplateUtils {

    public static String replaceValues(String userName, String password, String content) {
        return content.replace("{{userName}}", userName)
                      .replace("{{password}}", password);
    }

    public static String replaceValues( String content, String token) {
        return content.replace("{{token}}", token);
    }
}