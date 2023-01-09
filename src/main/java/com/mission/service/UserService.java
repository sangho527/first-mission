package com.mission.service;


import com.mission.domain.dto.user.UserDto;
import com.mission.domain.dto.user.UserJoinRequest;
import com.mission.domain.dto.user.UserJoinResponse;
import com.mission.domain.entity.User;
import com.mission.exception.AppException;
import com.mission.exception.ErrorCode;
import com.mission.repository.UserRepository;
import com.mission.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mission.exception.ErrorCode.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // 중복 체크를 하기 위해선 db를 확인해야함
    private final BCryptPasswordEncoder encoder;

    @Value(("${jwt.secret}")) // lombok이 아니라 springframework로 가져오기
    private String secretKey;

    private Long expireTimeMs = 1000 * 60 * 60l; // 1시간 , long이라 l 붙여줌

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        // userName 중복 체크하기
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, ErrorCode.USERNAME_DUPLICATED.getMessage());
                });

        User savedUser = userRepository.save(userJoinRequest.user(encoder.encode(userJoinRequest.getPassword())));
        return UserJoinResponse.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();

    }

    public String login(String userName, String password) {

        // userName 없음

        User selectedUser = userRepository.findByUserName(userName) // userRepo에서 유저네임을 찾는다
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, userName + "이 없습니다."));

        // password 틀림

        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드가 틀렸습니다.");
        }

        String token = JwtUtil.createToken(selectedUser.getUserName(), secretKey, expireTimeMs);
        return token;
    }

    public UserDto getUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(USERNAME_NOT_FOUND, USERNAME_NOT_FOUND.getMessage()));
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

}
