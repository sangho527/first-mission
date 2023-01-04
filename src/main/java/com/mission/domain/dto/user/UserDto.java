package com.mission.domain.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String password;

    public UserDto(Long id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
