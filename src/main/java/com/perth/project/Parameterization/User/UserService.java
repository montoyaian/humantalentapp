package com.perth.project.Parameterization.User;

import java.util.stream.Collectors;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Login.User.UserFuntions.UserFuntions;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadFileService;
import com.perth.project.Login.User.UserFuntions.UploadFileImplementation.UploadImageFile;
import com.perth.project.Login.exception.BusinessErrorCodes;
import com.perth.project.Login.exception.BusinessException;
import com.perth.project.Parameterization.User.UserTools.EditUserRequest;
import com.perth.project.Parameterization.User.UserTools.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFuntions userFuntions;
    private final UploadImageFile UploadImageFile;
    private final UploadFileService UploadFileService;
    public AuthResponse editUser(EditUserRequest request, String UserName, MultipartFile file) {
        Optional<User> optionalUser = userRepository.findByUsername(UserName);
        if (!optionalUser.isPresent()) {
            throw new BusinessException(
                            BusinessErrorCodes.BAD_REGISTER,
                            "Usuario no encontrado");
        }
        if (file != null) {
            UploadImageFile.deletePhoto(UserName);
            UploadFileService.handleFileUpload(file, UserName, "document", "labourSupport");
        }
        User user = optionalUser.get();
        user.setUsername(request.getUsername());
        user.setProfile(request.getProfile());
        user.setArea(request.getArea());
        user.setEmail(request.getEmail());

        userFuntions.Validation(user);
        userRepository.save(user);

        return AuthResponse.builder()
                .response("usuario editado correctamente")
                .build();
    }

     public AuthResponse deleteUser(String userName) {
       
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (!optionalUser.isPresent()) {
              throw new BusinessException(
                BusinessErrorCodes.BAD_REGISTER,
                "Usuario no encontrado");
        }
        User user = optionalUser.get();
        userRepository.delete(user);
        UploadImageFile.deletePhoto(userName);
        return AuthResponse.builder()
                .response("Usuario eliminado correctamente")
                .build();
    }

    public Object readUser(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<User> users = userRepository.findAll();
            List<UserResponse> userResponses = users.stream()
                    .map(user -> new UserResponse(user.getID(),user.getUsername(),user.getProfile(),user.getArea() ,user.getEmail(),user.getBlockedAccount()))
                    .collect(Collectors.toList());
            return userResponses;
        } else {
            Optional<User> optionalUser = userRepository.findByUsername(id);
            if (!optionalUser.isPresent()) {
                throw new BusinessException(
                    BusinessErrorCodes.BAD_REGISTER,
                    "Usuario no encontrado"); 
            }
            User user = optionalUser.get();
            return new UserResponse(user.getID(), user.getUsername(),user.getProfile(),user.getArea() ,user.getEmail(), user.getBlockedAccount());
            
        }
    }
    
}
