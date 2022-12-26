package com.mission.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 아이디입니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "회원가입이 필요합니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호를 다시 확인해주세요.");

    private HttpStatus httpStatus;
    private String message;
}
