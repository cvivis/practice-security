package com.example.springreview.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserLoginReq {
    private String userName;
    private String password;
}
