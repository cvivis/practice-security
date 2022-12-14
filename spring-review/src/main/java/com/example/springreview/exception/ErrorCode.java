package com.example.springreview.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// enum 이란 미리 지정해놓고 그 값 말고 다른 값들을 넣지 못하게 하여 예측한 범위 내에서 프로그램이 작동하게 하는 기능.
@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_DUPLICATED(HttpStatus.CONFLICT,"중복된 유저네임"), //예외 상황지정
    NOT_FOUND(HttpStatus.NOT_FOUND,"Not Found"),

    INVALID_PASSWORD(HttpStatus.NOT_FOUND,"없는 password"),

    TOKEN_ERROR(HttpStatus.CONFLICT,"토큰 오류");

    private HttpStatus status;
    private String message;
}
