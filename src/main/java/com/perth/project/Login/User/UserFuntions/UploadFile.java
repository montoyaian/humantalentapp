package com.perth.project.Login.User.UserFuntions;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {

    String handleFileUpload (MultipartFile file, String fileName, String fileType) throws Exception;

}
