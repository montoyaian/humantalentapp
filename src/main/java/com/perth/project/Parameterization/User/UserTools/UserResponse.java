package com.perth.project.Parameterization.User.UserTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String identification;
    private String username;
    private String profile;
    private String area;
    private String email;
    private Boolean IsBlockedAccount;
}
