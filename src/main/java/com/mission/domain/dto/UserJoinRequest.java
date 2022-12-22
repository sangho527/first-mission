package com.mission.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor// 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
}
