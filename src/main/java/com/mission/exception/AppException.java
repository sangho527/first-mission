package com.mission.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{

    private ErrorCode errorCode;
    String message; // 오류 난 것을 메시지로 출력
}
