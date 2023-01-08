package com.mission.controller;


import com.mission.domain.dto.Response;
import com.mission.domain.dto.user.UserJoinRequest;
import com.mission.domain.dto.user.UserJoinResponse;
import com.mission.domain.dto.user.UserLoginRequest;
import com.mission.domain.dto.user.UserLoginResponse;
import com.mission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join") // 회원가입
    public ResponseEntity<Response> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }



//    @PostMapping("/login") // 로그인
//    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
//        String token = userService.login(dto.getUserName(), dto.getPassword());
//        return ResponseEntity.ok().body(token);
//    }
}
