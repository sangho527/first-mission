package com.mission.domain.dto.user;



import com.mission.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;

    public UserJoinRequest(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public User user(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password)
                .build();
    }
}
