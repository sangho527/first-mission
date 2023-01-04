package com.mission.domain.dto.user;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserJoinRequest {
    private String userName;
    private String password;

    public UserJoinRequest(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
