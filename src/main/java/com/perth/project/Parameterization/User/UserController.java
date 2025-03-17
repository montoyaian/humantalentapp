package com.perth.project.Parameterization.User;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.Auth.Authservice;
import com.perth.project.Login.Auth.RegisterRequest;
import com.perth.project.Login.User.UserFuntions.DownloadImplemetation.DownloadDocumentFileSftp;
import com.perth.project.Login.User.UserFuntions.DownloadImplemetation.DownloadImageFileSftp;
import com.perth.project.Parameterization.User.UserTools.EditUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final DownloadDocumentFileSftp fileDownloadService;
    private final DownloadImageFileSftp imageDownloadService;
    private final UserService userService;
    private final Authservice authService;
    @PutMapping(value = "admin/user/edit/{UserName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> editUser(@RequestPart(required = false) EditUserRequest request, 
                                              @PathVariable("UserName") String UserName,
                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(userService.editUser(request, UserName, file));
    }

    @DeleteMapping(value = "admin/user/delete/{UserName}")
    public ResponseEntity<AuthResponse> deleteUser(@PathVariable("UserName") String UserName) {
        return ResponseEntity.ok(userService.deleteUser(UserName));
    }

    @GetMapping(value = "admin/user/read/{UserName}")
    public ResponseEntity<Object> getUser(@PathVariable("UserName") String UserName) {
        return ResponseEntity.ok(userService.readUser(UserName));
    }
    
    @GetMapping("user/document/download/{fileType}/{fileName}/{token}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, @PathVariable String fileType, @PathVariable String token) {
        return fileDownloadService.downloadFile(fileName, fileType, token);
    }

    @GetMapping("user/image/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        return imageDownloadService.downloadImageFile(fileName);
    }
    @PostMapping(value = "admin/user/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> register(@RequestPart @Valid RegisterRequest request,@RequestPart("file") MultipartFile file) {

        return ResponseEntity.ok(authService.register(request,file));
    }
}
