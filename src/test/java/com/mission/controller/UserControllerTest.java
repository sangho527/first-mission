package com.mission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mission.domain.dto.user.UserJoinRequest;
import com.mission.domain.dto.user.UserLoginRequest;
import com.mission.exception.AppException;
import com.mission.exception.ErrorCode;
import com.mission.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    // 자바 오브젝트를 json으로 만들어 주는 것
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {

        String userName = "Sangho";
        String password = "password";
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf()) // 포스트 호출할때 이게 들어있는 라이브러리가 spring security test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))) // userName, password를 전달
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        String userName = "Sangho";
        String password = "password";
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf()) // 포스트 호출할때 이게 들어있는 라이브러리가 spring security test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))) // userName, password를 전달
                .andDo(print())
                .andExpect(status().isConflict()) // 중복 여부를 확인
        ;
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception {
        String userName = "Sangho";
        String password = "password";

        when(userService.login(any(), any())) // id, password 를 받음
                .thenReturn("token"); // 로그인에 성공해 토큰을 돌려받음

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) // 포스트 호출할때 이게 들어있는 라이브러리가 spring security test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))) // userName, password를 전달
                .andDo(print())
                .andExpect(status().isOk()); // 통과
    }

    @Test
    @DisplayName("로그인 실패 - USERNAME 없음")
    @WithMockUser
    void login_fail() throws Exception {
        String userName = "Sangho";
        String password = "password";

        when(userService.login(any(), any())) // id, password 를 받음
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND, "")); // 로그인에 실패해 오류 출력 (유저네임 찾을 수 없음)

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) // 포스트 호출할때 이게 들어있는 라이브러리가 spring security test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))) // userName, password를 전달
                .andDo(print())
                .andExpect(status().isNotFound()); // 찾을 수 없음
    }

    @Test
    @DisplayName("로그인 실패 - PASSWORD 틀림")
    @WithMockUser
    void login_fail2() throws Exception {
        String userName = "Sangho";
        String password = "password";

        when(userService.login(any(), any())) // id, password 를 받음
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, "")); // 로그인에 실패해 오류 출력 (password 틀림)

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) // 포스트 호출할때 이게 들어있는 라이브러리가 spring security test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))) // userName, password를 전달
                .andDo(print())
                .andExpect(status().isUnauthorized()); // 비밀번호가 맞지않음
    }

}