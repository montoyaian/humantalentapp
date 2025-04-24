package com.perth.project.Parameterization.User.UserFuntions;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {

    String handleFileUpload (MultipartFile file, String fileName, String fileType,String fileInfo) ;

}
