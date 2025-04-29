package com.perth.project.EmployeeRecords;

import org.springframework.stereotype.Service;

import com.perth.project.Login.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeTools {
    private final UserRepository userRepository;

    public String getNameFromUserId(String userId) {
        userRepository.findById(userId)
                .orElse(null);
        return userRepository.findById(userId).get().getFirstName() + " " + userRepository.findById(userId).get().getLastName();
    }
}
