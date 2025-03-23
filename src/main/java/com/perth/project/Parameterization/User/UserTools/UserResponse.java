package com.perth.project.Parameterization.User.UserTools;

import com.perth.project.Login.User.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private Integer profile;
    private Integer area;
    private String email;
    private Role role;
    private Boolean IsBlockedAccount;
}
