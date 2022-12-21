package com.mission.service;


import com.mission.domain.User;
import com.mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // 중복 체크를 하기 위해선 db를 확인해야함

    public String join(String userName, String password) {

        // userName 중복 체크하기
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName + "는 이미 존재합니다.");
                });
        // 저장 하기
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
