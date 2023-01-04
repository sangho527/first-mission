package com.mission.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class UserJoinResponse {
    private Long userId;
    private String userName;

    public UserJoinResponse(Long userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }
}
