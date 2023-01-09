package com.mission.controller;


import com.mission.domain.dto.Response;
import com.mission.domain.dto.user.UserJoinRequest;
import com.mission.domain.dto.user.UserJoinResponse;
import com.mission.domain.dto.user.UserLoginRequest;
import com.mission.domain.dto.user.UserLoginResponse;
import com.mission.service.UserService;
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
    public ResponseEntity<Response<UserJoinResponse>> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }



    @PostMapping("/login") // 로그인
    public ResponseEntity<Response<UserLoginResponse>> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return ResponseEntity.ok().body(Response.success(new UserLoginResponse(token)));
    }

}
