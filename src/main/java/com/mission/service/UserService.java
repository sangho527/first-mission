package com.mission.service;


import com.mission.domain.User;
import com.mission.exception.AppException;
import com.mission.exception.ErrorCode;
import com.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // 중복 체크를 하기 위해선 db를 확인해야함
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password) {

        // userName 중복 체크하기
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "는 이미 존재합니다.");
                });
        // 저장 하기
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password)) // password를 인코딩해서 저장
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String userName, String password) {

        // userName 없음

        User selectedUser = userRepository.findByUserName(userName) // userRepo에서 유저네임을 찾는다
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));

        // password 틀림

        if (!encoder.matches(selectedUser.getPassword(), password))
                throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드가 틀렸습니다.");

        // 이곳에 exception 던지는 기능

        // exception 끝났으면 token을 만들어 주는 기능

        // 만든 토큰 리턴 기능


        return "token return";
    }
}
