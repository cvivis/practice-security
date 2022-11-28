package com.example.springreview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T>{
    private String resultCode;//에러나 성공 서버 코드
    private T result; // 모든 결과를 Response객체로 감아서 리턴하기 위한 선언, 결과를 통일성있게 작성할 수 있다.

    public static <T> Response<T> error(T result){
        return new Response("ERROR",result);
    }

    public static <T> Response<T> success(T result){
        return new Response("SUCCESS",result);
    }

}
