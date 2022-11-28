package com.example.springreview.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalReviewAppException extends RuntimeException{
    private ErrorCode errorCode; // 에러코드의 종류 지정
    private String dupName; // userName 받기

    @Override
    public String toString() {
        if(dupName == null) return errorCode.getMessage(); // 중복 이름 전달 X 시 에러 메세지만 지정
        return String.format("%s, %s",errorCode.getMessage(),dupName);
    }
}
