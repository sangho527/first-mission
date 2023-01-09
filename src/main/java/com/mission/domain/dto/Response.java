package com.mission.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response success(T result) {
        return new Response("SUCCESS", result);
    }

    public static <T> Response<T> error(String resultCode, T result) {
        return new Response(resultCode, result);
    }

}

