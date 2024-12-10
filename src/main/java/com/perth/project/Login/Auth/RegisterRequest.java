package com.perth.project.Login.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String firstName;
    String lastName;
    String identification;
    String profile;
    String area;
    String email;
}
