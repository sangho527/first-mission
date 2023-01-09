package com.mission.domain.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Builder
public class UserLoginRequest {
    private String userName;
    private String password;

    public UserLoginRequest(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
