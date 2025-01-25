package com.perth.project.Parameterization.User;

import java.util.stream.Collectors;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Login.User.User;
import com.perth.project.Login.User.UserRepository;
import com.perth.project.Parameterization.User.UserTools.EditUserRequest;
import com.perth.project.Parameterization.User.UserTools.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public AuthResponse editUser(EditUserRequest request, String UserName, MultipartFile file) {
        User user = User.builder()
                .username(request.getUsername())
                .identification(request.getIdentification())
                .profile(request.getProfile())
                .area(request.getArea())
                .email(request.getEmail())
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .response("usuario editado correctamente")
                .build();
    }

     public AuthResponse deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return AuthResponse.builder()
                    .response("Usuario eliminado correctamente")
                    .build();
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    public Object readUser(String id) {
        if ("all".equalsIgnoreCase(id)) {
            List<User> users = userRepository.findAll();
            List<UserResponse> userResponses = users.stream()
                    .map(user -> new UserResponse(user.getIdentification(), user.getUsername(),user.getProfile(),user.getArea() ,user.getEmail()))
                    .collect(Collectors.toList());
            return userResponses;
        } else {
            Optional<User> optionalUser = userRepository.findById(Integer.valueOf(id));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return new UserResponse(user.getIdentification(), user.getUsername(),user.getProfile(),user.getArea() ,user.getEmail());
            } else {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
        }
    }
}
