package com.perth.project.Parameterization.User.UserTools;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserRequest {
    private String profile;
    private String area;
    private String email;

}
